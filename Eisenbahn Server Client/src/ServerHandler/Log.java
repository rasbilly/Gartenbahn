package ServerHandler;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Log {

	private static final Log logger = new Log();
	private static SimpleDateFormat formatter = new SimpleDateFormat("[yyyy.MM.dd-HH:mm:ss] ");
	private static PrintWriter writer;
	private static String logFile;

	// Log.Warning(getClass().getName(), "Das hier ist die Nahricht");

	private Log() {
		try {
			String timeStamp = new SimpleDateFormat("yyyyMMdd-HHmmss").format(new Date());
			logFile = "log/" + timeStamp + "-Gartenbahn" + ".log";
			FileWriter fw = new FileWriter(logFile);
			writer = new PrintWriter(fw, true);
			writer.println("Gartebahn Steuerung gestartet!");
			//writer.println();
			writer.flush();
		} catch (IOException e) {
			System.out.println("fehler");
		}
	}

	public static void Error(String className, String msg, Exception e) {
		e.printStackTrace();
		msg = msg + "\n" + e;
		logWriter("ERROR", className, msg);
	}

	public static void Warning(String className, String msg, Exception e) {
		e.printStackTrace();
		msg = msg + "\n" + e;
		logWriter("WARN", className, msg);
	}

	public static void Warning(String className, String msg, String zusatz, Boolean consolenausgabe) {
		if (consolenausgabe) {
			System.out.println(msg + " " + zusatz);
		}
		msg = msg + " --> " + zusatz;
		logWriter("WARN", className, msg);

	}

	public static void Warning(String className, String msg, String zusatz, Exception e) {
		e.printStackTrace();
		msg = msg + " --> " + zusatz + "\n" + e;
		logWriter("WARN", className, msg);
	}

	public static void Info(String className, String msg) {
		logWriter("INFO", className, msg);
		System.out.println(msg);
	}

	public static void Track(String className, String msg, String zusatz) {
		msg = msg + " |Zusatz: " + zusatz;
		logWriter("Track", className, msg);
	}

	public static void Track(String className, String msg) {
		logWriter("Track", className, msg);
	}

	public static void Milestone(String className, String msg) {
		logWriter("Milestone", className, msg);
	}

	private static void logWriter(String typ, String location, String msg) {
		Date currentTime = new Date();
		String time = formatter.format(currentTime);
		writer.println(time + typ + " in " + location + ": " + msg);
		writer.flush();
	}

	public static void ladeDatei() {
		File file = new File(logFile);
		if (!file.canRead() || !file.isFile())
			System.exit(0);

		BufferedReader in = null;
		try {
			in = new BufferedReader(new FileReader(logFile));
			String zeile = null;
			while ((zeile = in.readLine()) != null) {
				System.out.println(zeile);
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			if (in != null)
				try {
					in.close();
				} catch (IOException e) {
				}
		}
	}

}
