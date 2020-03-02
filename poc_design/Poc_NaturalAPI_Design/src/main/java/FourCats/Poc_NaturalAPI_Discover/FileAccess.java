package FourCats.Poc_NaturalAPI_Discover;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class FileAccess implements FileAccessInterface {
	
	public String readDocument(String filename) throws FileNotFoundException {
		String filepath = "txt_documents/" + filename;
		String s, fileContent = new String();
		try(BufferedReader input = new BufferedReader(new FileReader(filepath))){
			while((s = input.readLine()) != null) {
				fileContent += s + " ";
			}
		} catch (IOException e) {
			fileContent="file non trovato";
		}
		return fileContent;
	}
}
