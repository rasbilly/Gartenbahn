/*
 GARTENBAHN - Steuerung - Zug
 
 noch zu erledigen:
 Kommentar: lib-> rfidmaster->MFRC522.cpp -> In der Methode PCD_Init() vor PCD_AntennaOn() --> PCD_WriteRegister(RFCfgReg, (0x07<<4)); einfügen  // Set Rx Gain to max 111 48 dB HEX = 0x07 // Hinzugefuegt 23.04.17 tobias
 erhört die Antennenleistung auf Max

 //Datei -> Voreinstellungen -> Zus�tzliche Boardverwalter-URLs:
 http://arduino.esp8266.com/stable/package_esp8266com_index.json



 */

//Bibliothek einbinden
#include <ESP8266WiFi.h>		// ESP8266WiFi Built-In by Ivan Grokhotkov
#include <SPI.h>
#include <MFRC522.h> 			// RFID // https://github.com/miguelbalboa/rfid

//Pins definieren
#define SS_PIN 15 				// RFID
#define RST_PIN 16 				
MFRC522 mfrc522(SS_PIN, RST_PIN);  
#define relaisPin 5 			// D1 Relais f�r Fahrtrichtungs�nderung
#define tempoPin 0  			// D3 Transistor Tempo regulieren
#define trigger 2 				// D4 Ultraschall Sensor Trigger
#define echo 4  				// D2 Ultraschall Sensor Echo

//Variabeln Erstellen
int tempo, uidTag;
long dauer = 0;  				// Messung Ultraschall Sensor
long entfernung = 0; 			// Messung Ultraschall Sensor

//WLAN
WiFiClient client;
const char* ssid = "Gartenbahn"; 	// Name des Netzwerkes
//Sever
const uint16_t port = 603;  	// Port des Servers
const char * host = "IP";  		// IP des Servers

void setup() {
	analogWriteFreq(200);
	pinMode(relaisPin, OUTPUT);
	pinMode(tempoPin, OUTPUT);
	pinMode(trigger, OUTPUT);
	pinMode(echo, INPUT);

	analogWrite(tempoPin, 0); // Sofortiges 0 setzen, damit Zug nicht losfahren tut beim aufsetzen auf die Schiene.

	Serial.begin(115200); //Im Seriellen Monitor auf selben Port achten
	SPI.begin();

	//WLAN Verbinden
	WiFi.begin(ssid);
	Serial.print("Verbindungsaufbau");
	while (WiFi.status() != WL_CONNECTED) {
		Serial.print(".");
		delay(300);
	}
	if (WiFi.status() != WL_CONNECTED) {
		Serial.println("keine Verbindung");
	} else {
		Serial.println(" erfolgreich! \n");
		Serial.print("Verbunden mit: ");
		Serial.println(ssid);
		Serial.print("Signalstaerke: ");
		int rssi = WiFi.RSSI();
		Serial.print(rssi);
		Serial.println(" dBm");
		Serial.print("IP-Adresse: ");
		Serial.println(WiFi.localIP());
	}


	client.connect(host, port);
	if (client.connected()) {
		Serial.println("Verbunden mit Host");
	}
	if (!client.connected()) {
		Serial.println("Verbindung zum Host fehlgeschlagen");
	}

	//RFID
	mfrc522.PCD_Init();

	Serial.println("LOS");

	delay(10);
	client.print("r");
	client.print("#");
	client.println("0");  //client.println("r#0");
	client.flush();

} // Ende Setup

int zaehler = 0; // Abstandsmessung austricksen

void loop() {
	zaehler++;
	if (tempo > 0) {
		if (zaehler > 12) { // um verzögerungen zu vermeiden
			abstandMessung();
			zaehler = 0;
		}
	}
	rfid();

//Netzwerk lauschen
	if (client.available()) {
		String line = client.readStringUntil('\r');
		Serial.print("Empfangen: ");
		Serial.println(line);
		if (line.startsWith("heartbeat")) {
			client.print("h");
			client.print("#");
			client.println("0");
			client.flush();
		}
		if (line.startsWith("t")) {
			String f = line.substring(1);
			int tt = f.toInt();
			zugSteuerung(tt);
		}
	}

	if (!client.connected()) {
		Serial.println("ENDE");
		if (!client.connect(host, port)) {
			Serial.println("connection failed");
			delay(50);
			return;
		}
		if (client.connected()) {
			Serial.println("Verbunden");
			delay(50);
			client.print("r");
			client.print("#");
			client.println("0");  //client.println("r#0"); möglich?
			client.flush();
		}
	}
}

/*
 * Tempo setzen und an Server senden
 */
void setTempo(int t) {

	client.print("t");
	client.print("#");
	client.println(t);  
	client.flush();
	Serial.print("tempo ");
	Serial.println(t);
	tempo = t;
}

/*
 * Prueft ob hindernis in 40cm sichtweite.
 * wenn ja, führt Sicherheitsmessung durch
 * Fall 1: zwischen 35 und und 20 Tempo drosseln 
 * Fall 2: unter 20 Stopp
 * Fall 3: größer 40 Abbruch
 */
void abstandMessung() {
	messen();
	if (entfernung >= 40 || entfernung <= 0) {
		Serial.print("-");
	} else {
		Serial.print("Erste Messung: ");
		Serial.print(entfernung);
		Serial.println(" cm");
		messen();
		//Langsamer fahren
		if (entfernung <= 35 && entfernung > 20) {
			Serial.println("");
			Serial.print("!!   LANGSAMER   !! : ");
			Serial.print(entfernung);
			Serial.println(" cm");
			Serial.println("");
			if (tempo >= 2) {
				zugSteuerung(tempo - 1);
				setTempo(tempo);
			} else if (tempo <= 1) {
				zugSteuerung(0);
			}
		}
		//Einggreifen STOPP
		else if (entfernung <= 20 && entfernung >= 0) {
			Serial.println("");
			Serial.print("!!   STOPP  !! : ");
			Serial.print(entfernung);
			Serial.println(" cm");
			Serial.println("");
			zugSteuerung(0);
		} else if (entfernung >= 40 || entfernung <= 0) {
			Serial.println("Doch nicht");
		}
	}
}

/*
 * Misst die Entfernung
 * @return entferung
 */
long messen() {
	digitalWrite(trigger, LOW);
	delay(4);
	digitalWrite(trigger, HIGH);
	delay(9);
	digitalWrite(trigger, LOW);
	dauer = pulseIn(echo, HIGH);
	entfernung = (dauer / 2) * 0.03432;
	Serial.println(entfernung);
	return entfernung;

}

/*
 * Transistorsteuerung
 * 
 * Berechnung:
 - Tansistor von 0 bis 1024
 - Zug Start bei 110
 - Rest 914
 - Rest / 10 = 91,4
 - Ergebnis mal Variable fahrt
 */
void zugSteuerung(int fahrt) {

	if (fahrt >= 1 && fahrt <= 10) {
		digitalWrite(relaisPin, HIGH);
		setTempo(fahrt);
		analogWrite(tempoPin, (130 + (fahrt * 178)));

	} else if (fahrt == 0) {
		analogWrite(tempoPin, 0);
		setTempo(fahrt);

	} else if (fahrt <= -1 && fahrt >= -10) {
		digitalWrite(relaisPin, LOW);
		analogWrite(tempoPin, (130 + (fahrt * -178)));
		setTempo(fahrt);

	} else {
		Serial.println("!! ungültiger Parameter");
	}
}

/*
 * RFID auslesen und dann Server senden
 */
void rfid() {
	// Wenn eine RFID-Karte in der Nähe ist:
	if (mfrc522.PICC_IsNewCardPresent()) {
		if (!mfrc522.PICC_ReadCardSerial()) {
		} //schleife unterbinden
		long i = mfrc522.uid.uidByte[0] + mfrc522.uid.uidByte[1]
				+ mfrc522.uid.uidByte[2] + mfrc522.uid.uidByte[3];
		mfrc522.PICC_HaltA(); // Stop reading
		Serial.print("Tag: "); //Serial.println(uidTag);  //ausgabe im Seriellen Monitor
		Serial.println(i);
		Serial.println("Umwandeln... ");
		if (i == 383) {
			uidTag = 1;
		} else if (i == 342) {
			uidTag = 2;
		} else if (i == 473) {
			uidTag = 3;
		} else if (i == 403) {
			uidTag = 4;
		} else if (i == 388) {
			uidTag = 5;
		} else if (i == 731) {
			uidTag = 6;
		} else if (i == 537) {
			uidTag = 7;
		} else if (i == 499) {
			uidTag = 8;
		} else if (i == 758) {
			uidTag = 9;
		} else if (i == 491) {
			uidTag = 10;
		} else if (i == 571) {
			uidTag = 11;
		} else if (i == 566) {
			uidTag = 12;
		} else if (i == 516) {
			uidTag = 13;
		} else if (i == 620) {
			uidTag = 14;
		} else if (i == 515) {
			uidTag = 15;
		}
		Serial.print("Wert zum Übertragen: ");
		Serial.println(uidTag);

		client.print("p");
		client.print("#");
		client.println(uidTag);
		client.flush();

	}
}

