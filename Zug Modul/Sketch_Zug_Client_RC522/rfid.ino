int rfid() {
  int uidTag;
  // Wenn eine RFID-Karte in der Nähe ist:
  if (mfrc522.PICC_IsNewCardPresent()) {
    if ( ! mfrc522.PICC_ReadCardSerial()) {} //schleife unterbinden
    long i =  mfrc522.uid.uidByte[0] + mfrc522.uid.uidByte[1] + mfrc522.uid.uidByte[2] + mfrc522.uid.uidByte[3];
    mfrc522.PICC_HaltA(); // Stop reading
    Serial.printf("Tag: %i\n", i); //Serial.println(uidTag);  //ausgabe im Seriellen Monitor
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
    Serial.printf("Wert zum Übertragen: %u\n", uidTag);
    client.print("p#");
    client.println(uidTag);
    client.flush();
    return uidTag;
  }
}




