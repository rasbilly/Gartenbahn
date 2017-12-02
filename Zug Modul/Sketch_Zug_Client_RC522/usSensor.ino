/**
 * Reagiert auf die vorliegenen messdaten
 */
void abstandMessung() {
  long entfernung = messen();
  if (entfernung >= 45 || entfernung <= 0) {
    Serial.print("-");
  } else {
    Serial.printf("\nErste Messung: %e cm\n", entfernung);
    entfernung = messen();
    //Langsamer fahren
    if (entfernung <= 40 && entfernung > 30) {
      Serial.printf("\n!!   LANGSAMER   !! : %e cm\n", entfernung);
      if (tempo >= 2) {
        zugSteuerung(tempo - 1);
        sendeTempo(tempo-1);
      }
      else if (tempo <= 1) {
        zugSteuerung(0);
        sendeTempo(0);
      }
    }
    //Einggreifen STOPP
    else if (entfernung <= 30 && entfernung >= 0) {
      Serial.printf("\n!!   STOPP  !! : %e cm\n", entfernung);
      zugSteuerung(0);
      sendeTempo(0);
    }
    else if (entfernung >= 40 || entfernung <= 0) {
      Serial.println("Doch nicht");
    }
  }
}


/**
 * Führt die Messung durch
 */
long messen() {
  digitalWrite(trigger, LOW);
  delay(2);
  digitalWrite(trigger, HIGH);
  delay(9);
  digitalWrite(trigger, LOW);
  long dauer = pulseIn(echo, HIGH);
  long entfernung = (dauer / 2) * 0.03432;
  Serial.printf("Entfernung: %e \n",entfernung);
  return entfernung;
}
