package FourCats.Poc_NaturalAPI_Discover;

import java.io.*;

public class FileAccess implements FileAccessInterface {
	
	public String readDocument(String filename) throws FileNotFoundException {
		String filepath = "txt_documents/" + filename;
		String s, fileContent = new String();
		try{
			BufferedReader input = new BufferedReader(new FileReader(filepath));
			while((s=input.readLine()) != null){
				fileContent += s + " ";
			}

			input.close();
			
		}catch(IOException e){
			//modificare gestione eccezioni
			fileContent="file non trovato";
		}

		/*try(BufferedReader input = new BufferedReader(new FileReader(filepath))){
			while((s = input.readLine()) != null) {
				fileContent += s;
			}
		} catch (IOException e) {
			fileContent="file non trovato";
		}*/
		return fileContent;
	}
}
