
/*
   noch zu erledigen:
      Kommentar: lib-> rfidmaster->MFRC522.cpp -> PCD_WriteRegister(RFCfgReg, (0x07<<4)); // Set Rx Gain to max // Hinzugefuegt 23.04.17 tobias
    erhört die Antennenleistung auf Max
*/


//Bibliothek einbinden
#include <ESP8266WiFi.h>
#include <SPI.h>
//RFID
#include <MFRC522.h>

//Pins definieren
//RFID
#define SS_PIN 15
#define RST_PIN 16
MFRC522 mfrc522(SS_PIN, RST_PIN);
//ZugSteuerung
#define relaisPin 5 //D1
#define tempoPin 0  //D3
#define trigger 2 //D4-------TX ???
#define echo 4  // D2 -------  RX ???

//Variabeln Erstellen
int tempo;
int uidTag;

long dauer = 0;  //Messung
long entfernung = 0; //Messung



WiFiClient client;
//WLAN

const char* ssid     = "SSID";
const char* password = "PW";
//Sever
const uint16_t port = 603;  // Port des Servers
const char * host = "IP";  // IP des Servers





void setup() {
  Serial.begin(115200); //Verbindung zum PC aufbauen //Im Seriellen Monitor auf selben Port achten
  SPI.begin();

  //WLAN Verbinden
  WiFi.begin(ssid, password);
  Serial.print("Verbindungsaufbau");
  while (WiFi.status() != WL_CONNECTED) {
    Serial.print(".");
    delay(500);
  }
  if (WiFi.status() != WL_CONNECTED) {
    Serial.println("keine Verbindung");
  }
  else {
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
  delay(30);

  // Kein While , damit im falle keiner Verbindung die Automatische bremse (abstandMessung) greift.
  client.connect(host, port);
  if (client.connected()) {
    Serial.println("Verbunden mit Host");
  } if (!client.connected()) {
    Serial.println("Verbindung zum Host fehlgeschlagen");
  }


  //RFID
  mfrc522.PCD_Init();

  pinMode(relaisPin, OUTPUT);
  pinMode(tempoPin, OUTPUT);
  pinMode(trigger, OUTPUT);
  pinMode(echo, INPUT);

  Serial.println("LOS");

  delay(400);
  client.print("r");
  client.print("#");
  client.println("0");
  client.flush();

}

int zaehler = 0;

void loop() {
  zaehler = zaehler + 1;
  delay(1);
  if (tempo > 0) {
    if (zaehler > 25) { // um verzögerungen zu vermeiden
      abstandMessung();
      zaehler = 0;
    }
  }
  rfid();

  if (client.available()) {
    String line = client.readStringUntil('\r');
    Serial.print("Empfangen: "); Serial.println(line);
    if ( line.startsWith("heartbeat")) {
      client.println("ichLebe");
      client.flush();
    }
    if (line.startsWith("t")) {
    String f = line.substring(1);
      int tt = f.toInt();
      zugSteuerung(tt);
    }
    if (line.startsWith("Ende")) {
    client.println("bye bye");
      client.flush();
      client.stop();
    }
  }

  if (!client.connected()) {
    Serial.println("ENDE");
    if (!client.connect(host, port)) {
      Serial.println("connection failed");
      delay(10);
      return;
    }
    if (client.connected()) {
      Serial.println("Verbunden");
      delay(100);
      client.print("r");
      client.print("#");
      client.println("0");
      client.flush();
    }
  }
}



void setTempo(int t) {
  //client.print("tempo "); client.println(t);
  //client.flush();

  client.print("t");
  client.print("#");
  client.println(t);
  client.flush();


  Serial.print("tempo "); Serial.println(t);
  tempo = t;
}




void abstandMessung() {
  messen();
  if (entfernung >= 30 || entfernung <= 0) {
    Serial.print("-");
  } else {
    Serial.print("Erste Messung: "); Serial.print(entfernung); Serial.println(" cm");
    delay(1);
    messen();
    //Langsamer fahren
    if (entfernung <= 28 && entfernung > 13) {
      Serial.println(""); Serial.print("!!   LANGSAMER   !! : "); Serial.print(entfernung); Serial.println(" cm"); Serial.println("");
      if (tempo >= 2) {
        zugSteuerung(tempo - 2);
        setTempo(tempo);
      }
      else if (tempo <= 1) {
        zugSteuerung(0);
      }
    }
    //Einggreifen STOPP
    else if (entfernung <= 13 && entfernung >= 0) {
      Serial.println(""); Serial.print("!!   STOPP  !! : "); Serial.print(entfernung); Serial.println(" cm"); Serial.println("");
      zugSteuerung(0);
    }
    else if (entfernung >= 10 || entfernung <= 0) {
      Serial.println("Doch nicht");
    }
  }
}

long messen() {
  digitalWrite(trigger, LOW);
  delay(5);
  digitalWrite(trigger, HIGH);
  delay(10);
  digitalWrite(trigger, LOW);
  dauer = pulseIn(echo, HIGH);
  entfernung = (dauer / 2) * 0.03432;
  return entfernung;
}

void zugSteuerung(int fahrt) {

  /*Berechnung:
     - Tansistor von 0 bis 1024
     - Zug Start bei 110
     - Rest 914
     - Rest / 10 = 91,4
     - Ergebnis mal Variable fahrt
  */

  if (fahrt >= 1 && fahrt <= 10 ) {
    digitalWrite(relaisPin, HIGH);
    setTempo(fahrt);
    analogWrite(tempoPin, (120 + (fahrt * 89)));

  } else if (fahrt == 0) {
    analogWrite(tempoPin, 0);
    setTempo(fahrt);

  } else if (fahrt <= -1 && fahrt >= -10 ) {
    digitalWrite(relaisPin, LOW);
    analogWrite(tempoPin, (120 + (fahrt * -89)));
    setTempo(fahrt);

  } else {
    Serial.println("!! ungültiger Parameter");
  }
}


void rfid() {
  // Wenn eine RFID-Karte in der Nähe ist:
  if (mfrc522.PICC_IsNewCardPresent()) {
    if ( ! mfrc522.PICC_ReadCardSerial()) {} //schleife unterbinden
    long i =  mfrc522.uid.uidByte[0] + mfrc522.uid.uidByte[1] + mfrc522.uid.uidByte[2] + mfrc522.uid.uidByte[3];
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




