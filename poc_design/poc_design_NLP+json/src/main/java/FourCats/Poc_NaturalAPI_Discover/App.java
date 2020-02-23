package FourCats.Poc_NaturalAPI_Discover;

import java.io.FileNotFoundException;

/**
 * Hello world!
 *
 */
public class App 
{	
    public static void main( String[] args ) throws FileNotFoundException
    {
      
    	FileAccessInterface file = new FileAccess();
    	String documento1 = "prova.feature";
    	Document doc = new Document("prova","Io", 2020,file.readDocument(documento1),"it");
    	
        System.out.println(doc.getContent());
        
        //Separa il contenuto del document quando trova un "."
        String[] sentences = doc.getContent().split("\\.");
        
        //Esegue la lemmatization del documento e lo stampa
        LemmatizerAccessInterface lemmatizer = new LemmatizerAccess();
        LemmatizerData result = lemmatizer.lemmatizeSentence(doc.getContent());
        for (LemmatizerData.WordTag wtag : result.getList()) {
        	System.out.println(wtag.getValue() + " " + wtag.getTag() + " " + wtag.getLemma());
        }
        
        ParserAccessInterface depparser = new ParserAccess();
                
        BDL bdl = new BDL();
        for (LemmatizerData.WordTag wtag : result.getList()) {
        	if(wtag.getTag().contains("NN")) {
        		bdl.addNoun(wtag.getLemma());
        	}
        	if(wtag.getTag().contains("VB")) {
        		bdl.addVerb(wtag.getLemma());
        	}
        }
            
        for(WordCounter w : bdl.getNouns()) {
        	System.out.println("NOME: " + w.getWord() + " " + w.getCount());
        }
        for(WordCounter w : bdl.getVerbs()) {
        	System.out.println("VERBO: " + w.getWord() + " " + w.getCount());
        }
        
        
    }
    
}
