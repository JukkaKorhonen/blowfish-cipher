package fi.jukkakorhonen.cipher.file;

import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import fi.jukkakorhonen.exception.CipherException;

public class FilesReader extends InputStreamReader {
	
	public static void main(String[] args) throws IOException {
		String fileread = readFile();
		System.out.println(fileread);
	}
	
	/**
	 * Read file named passworddata.txt
	 * @return String read file
	 * @throws CipherException
	 */
	public static String readFile() throws CipherException {
		StringBuilder sb = new StringBuilder();
		try {
			FileReader fr = new FileReader("passworddata.txt");
			int i;
			while ((i = fr.read()) != -1)
				sb.append((char) i);
			fr.close();
		} catch (Exception e) {
			throw new CipherException(e, "Cannot open file");
		}
		return sb.toString();
	}

	public FilesReader(InputStream in) {
		super(in);
	}

}
