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
	
	public BDL readBDL(String project) throws FileNotFoundException {
		BDL bdl = new BDL();
		String filepathNouns = project + ".nouns.bdl.csv";
		String filepathVerbs = project + ".verbs.bdl.csv";
		String filepathPredicates = project + ".predicates.bdl.csv";
		String s;
		try {
			//Opening of nouns, verbs and predicates files.
			BufferedReader inputNouns = new BufferedReader(new FileReader(filepathNouns));
			BufferedReader inputVerbs = new BufferedReader(new FileReader(filepathNouns));
			BufferedReader inputPredicates = new BufferedReader(new FileReader(filepathNouns));
			
			while((s=inputNouns.readLine())!=null) {
				String[] parts = s.split(",");
				for(Integer i=0;i<Integer.parseInt(parts[1]);i++) {
					bdl.addNoun(parts[0]);
				}
			}
			inputNouns.close();
			
			while((s=inputVerbs.readLine())!=null) {
				String[] parts = s.split(",");
				for(Integer i=0;i<Integer.parseInt(parts[1]);i++) {
					bdl.addVerb(parts[0]);
				}
			}
			inputVerbs.close();
			
			while((s=inputPredicates.readLine())!=null) {
				String[] parts = s.split(",");
				for(Integer i=0;i<Integer.parseInt(parts[1]);i++) {
					bdl.addPredicate(parts[0]);
				}
			}
			inputPredicates.close();
			
		}catch(IOException e){
			
		}
		
		return bdl;
	}
}
