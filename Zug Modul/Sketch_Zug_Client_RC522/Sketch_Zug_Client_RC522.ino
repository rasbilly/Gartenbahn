/*
 GARTENBAHN - Steuerung - Zug

 noch zu erledigen:
 Kommentar: lib-> rfidmaster->MFRC522.cpp -> In der Methode PCD_Init() vor PCD_AntennaOn() --> PCD_WriteRegister(RFCfgReg, (0x07<<4)); einfügen  // Set Rx Gain to max 111 48 dB HEX = 0x07 // Hinzugefuegt 23.04.17 tobias
 erhört die Antennenleistung auf Max

 //Datei -> Voreinstellungen -> Zus�tzliche Boardverwalter-URLs:
 http://arduino.esp8266.com/stable/package_esp8266com_index.json

 ############ TODO
 MC33926 und RC522
 */

//Bibliothek einbinden
#include <ESP8266WiFi.h>
#include <SPI.h>
#include <MFRC522.h> //RFID

//Pins definieren
MFRC522 mfrc522(15, 16); //(SS, RST) //RFID
#define richtungB 0 //D3
#define richtungA 2 //D4
#define tempoPin 5  //D1
#define trigger 4 //D2
#define echo 3  //RX

int tempo;
WiFiClient client; //WLAN
const uint16_t port = 603;              // Port des Servers
const char * host = "192.168.10.100";   // IP des Servers

void setup() {
	analogWriteFreq(200);
	pinMode(richtungA, OUTPUT);
	pinMode(richtungB, OUTPUT);
	pinMode(tempoPin, OUTPUT);
	pinMode(trigger, OUTPUT);
	pinMode(echo, INPUT);

	analogWrite(tempoPin, 0);

	Serial.begin(115200); //Verbindung zum PC aufbauen //Im Seriellen Monitor auf selben Port achten
	SPI.begin();

	//WLAN Verbinden
	WiFi.begin("Gartenbahn", "Gartenbahn"); //SSID und Passwort
	WiFi.config(IPAddress(192, 168, 10, 48), IPAddress(192, 168, 10, 1),
			IPAddress(255, 255, 255, 0), IPAddress(192, 168, 10, 1));
	Serial.print("Verbindungsaufbau");
	while (WiFi.status() != WL_CONNECTED) {
		Serial.print(".");
		delay(100);
	}
	Serial.print(" erfolgreich! \nIP-Adresse:");
	Serial.println(WiFi.localIP());

	//Server Verbinden
	serverVerbinden();

	//RFID
	mfrc522.PCD_Init();

	Serial.println("Los gehts :)");

	//Tempo Request
	delay(200);
	client.println("r#0");
	client.flush();
}

int zaehler = 0;

void loop() {

	if (tempo > 0) {
		if (zaehler > 25) { // um verzögerungen zu vermeiden
			abstandMessung();
			zaehler = 0;
		}
	}

	rfid();
	readData();
	checkConnection();
	zaehler++;

} //ENDE LOOP ################################################## ENDE LOOP  ##########################

void readData() {
	if (client.available()) {
		String line = client.readStringUntil('\r');
		Serial.print("Empfangen: ");
		Serial.println(line);
		if (line.startsWith("h")) {
			client.println("h#0");
			client.flush();
			anfahrenCheck(line.substring(1).toInt());
		} else if (line.startsWith("t")) { //Tempo
			anfahrenCheck(line.substring(1).toInt());
		} else if (line.startsWith("l")) { //langsames Bremsen
			langsammesAbbremsen();
		}
	}
}

void checkConnection() {
	if (!client.connected()) {
		Serial.println("Verbindung zum Server Unterbrochen");
		if (!client.connect(host, port)) {
			Serial.println("connection failed");
			delay(80);
			serverVerbinden();
			return;
		}
		if (client.connected()) {
			Serial.println("Verbunden");
			delay(100);
			client.println("r#0");
			client.flush();
		}
	}
}

void serverVerbinden() {
	while (!client.connected()) {
		client.connect("192.168.10.100", port);
		if (client.connected()) {
			break;
		}
		client.connect("192.168.10.101", port);
		if (client.connected()) {
			break;
		}
		client.connect("192.168.10.102", port);
	}
	Serial.println("Verbunden mit Server");
}

