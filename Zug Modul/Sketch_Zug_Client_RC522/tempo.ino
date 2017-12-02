
void sendeTempo(int t) {
  client.print("t#"); client.println(t);
  client.flush();
  Serial.printf("tempo %t gesendet", t);
}
/**
   bisher nur vorwärts
*/
void anfahrenCheck(int i) {
  if (tempo < 3 && tempo >= 0) {
    zugSteuerung(i);
  } else {
    int zielTempo = 130 + (i * 178);
    int helfer = 130,z;
    while (zielTempo > helfer) {
      analogWrite(tempoPin, helfer);
      helfer += 25;
      rfid();
      if (z % 2 == 0) {
        delay(5);
      } else {
        abstandMessung();
      }
    }
    zugSteuerung(i);
  }
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
    digitalWrite(richtungA, HIGH);
    digitalWrite(richtungB, LOW);
    tempo = fahrt;
    analogWrite(tempoPin, (130 + (fahrt * 178)));

  } else if (fahrt == 0) {
    analogWrite(tempoPin, 0);
    digitalWrite(richtungA, LOW);
    digitalWrite(richtungB, LOW);
    tempo = fahrt;

  } else if (fahrt <= -1 && fahrt >= -10 ) {
    digitalWrite(richtungA, LOW);
    digitalWrite(richtungB, HIGH);
    analogWrite(tempoPin, (130 + (fahrt * -178)));
    tempo = fahrt;

  } else {
    Serial.println("!! ungültiger Parameter");
  }
}




void langsammesAbbremsen() {
  Serial.println("Langsames Abbremsen von:"); Serial.println(tempo);
  int i = (tempo * 178);
  while (i > 150) {
    Serial.println(i);
    analogWrite(tempoPin, i);
    i = i - 20;
    delay(1);
    if(rfid()==342){ //Im Bahnhof
      zugSteuerung(0);
    }
    if (i < 180) {
      break;
    }
    rfid();
  }
  zugSteuerung(0);
}

