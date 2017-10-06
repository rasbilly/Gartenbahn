//Bibliothek einbinden
#include <ESP8266WiFi.h>
#include <SPI.h>


WiFiClient client;
//WLAN
const char* ssid     = "SSID";
const char* password = "PW";
//Sever
const uint16_t port = 602;
const char * host = "IP des Servers";


// Initialisierung benötigter Variablen
int counter = 0;
boolean Richtung;
int Pin_clk_Letzter;
int Pin_clk_Aktuell;

int helfer;




// Definition der Eingangs-Pins
int pin_clk = 12;
int pin_dt = 13;
int button_pin = 14;


void setup()
{
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
  
  // Eingangs-Pins werden initialisiert...
  pinMode (pin_clk, INPUT);
  pinMode (pin_dt, INPUT);
  pinMode (button_pin, INPUT_PULLUP);

  // ...und deren Pull-Up Widerstände aktiviert
  digitalWrite(pin_clk, true);
  digitalWrite(pin_dt, true);


  // Initiales Auslesen des Pin_CLK
  Pin_clk_Letzter = digitalRead(pin_clk);
  Serial.begin (115200);
  delay(100);
  Serial.println("..");
  Serial.println("..");
}


void loop()
{
  if (!client.connected()) {
    Serial.println("ENDE");
    if (!client.connect(host, port)) {
      Serial.println("connection failed");
      delay(3000); //Nur zur Probe
      return;
    }}

  // Auslesen des aktuellen Statuses
  Pin_clk_Aktuell = digitalRead(pin_clk);

  // Überprüfung auf Änderung
  if (Pin_clk_Aktuell != Pin_clk_Letzter)
  {

    if (digitalRead(pin_dt) != Pin_clk_Aktuell) {
      if (counter > -20) {
        counter--;
      }
    }
    else {
      if (counter < 20) {
        counter++;
      }
    }
    if (counter % 2 == 0 || counter == 0) {
      if (helfer != counter) {
        if (counter > 0) {
          Serial.print("Vorwärts - ");
        } else if ( counter < 0) {
          Serial.print("Rückwärts - ");
        } else if (counter == 0) {
          Serial.print("Stopp - ");
        }
        Serial.print("Tempo: ");
        Serial.println(counter / 2);
        int help = counter/2;
        client.print("dreh"); client.println(help);
        client.flush();
        helfer = counter;
      }
    }
  }
  Pin_clk_Letzter = Pin_clk_Aktuell;

  delay(2);

  //Taster
  if (digitalRead(button_pin) == LOW)
  { counter = 0;
    Serial.println("Stopp - Tempo: 0");
    client.print("dreh"); client.println(0);
        client.flush();
    while (digitalRead(button_pin) == LOW) {
      delay(10);
    }

  }


}
