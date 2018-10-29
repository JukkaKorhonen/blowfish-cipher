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
			writer = new PrintWriter("passworddata.txt", "ISO_8859_1");
			line = line.trim();
			writer.print(line);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} finally {
			writer.close();
		}
	}
}
