package fi.jukkakorhonen.cipher.file;

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

public class FileWriter {
	
	/**
	 * Write String to a file
	 * Fixed file name passworddata.txt
	 * @param line
	 */
	public static void writeFile(String line) {
		PrintWriter writer = null;
		try {
			writer = new PrintWriter("passworddata.txt", "UTF-8");
			writer.println(line);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} finally {
			writer.close();
		}
	}
}
