package GPIO;
import com.pi4j.component.lcd.impl.I2CLcdDisplay;
import com.pi4j.io.i2c.I2CBus;
public class LcdDisplay {
 

    public static void main(String[] args) throws Exception {
    	System.out.println("Start Programm");
          I2CLcdDisplay lcd = new I2CLcdDisplay(2, 21,
                          I2CBus.BUS_1, 0x3F, 3, 0, 1, 2, 7, 6, 5, 4);
          System.out.println("Start set Cursor");
          lcd.setCursorHome();
          System.out.println("Start write");
          lcd.write("Starte Gartenbahn");
          lcd.setCursorPosition(1, 0);
          lcd.write("Version ");
          System.out.println("End");
    }
}