package FourCats.Poc_NaturalAPI_Discover;


import java.io.FileNotFoundException;
import java.util.LinkedList;

public class App 
{	
    public static void main( String[] args ) throws FileNotFoundException,Exception
    {
    	//Lettura del primo documento
    	FileAccessInterface file = new FileAccess();
    	String documento1 = "atm.txt";
    	Document doc = new Document("doc1","Io", 2020,file.readDocument(documento1),"en");
        
        //Esegue lemmatization del documento, ricava nomi e verbi
        LemmatizerAccessInterface lemmatizer = new LemmatizerAccess();
        LemmatizerData result = lemmatizer.lemmatizeSentence(doc.getContent());

        //Parsing delle dipendenze, ricava i predicati
        ParserAccessInterface depparser = new ParserAccess();
        ParserData data = depparser.parseSentence(doc.getContent());

        //GENERAZIONE BDL
        System.out.println("---------------------- GENERAZIONE BDL ----------------------" );
        BDL bdl = new BDL();
        bdl.useLemmatizerData(result); //Crea dipendenza?
        bdl.useParseData(data);
        bdl.saveToFile("Project");
        System.out.println("BDL generato correttamente");
        
        //INTEGRAZIONE BDL CON SECONDO DOCUMENTO
        System.out.println("----------------INTEGRAZIONE BDL----------------------------");
        BDL loadedBdl = file.readBDL("Project");
        String documento2 = "atm_wikipedia.txt";
        Document otherDoc = new Document("doc2","Io",2020,file.readDocument(documento2),"en");
        loadedBdl.useLemmatizerData(lemmatizer.lemmatizeSentence(otherDoc.getContent()));
        loadedBdl.useParseData(depparser.parseSentence(otherDoc.getContent()));
        //Ordering the lists by the number of occurences
        loadedBdl.order();
        loadedBdl.saveToFile("Project_with_integration");
        System.out.println("BDL integrato correttamente");
        
        /*LinkedList<WordCounter> nouns = bdl.getNouns();
        for(WordCounter w : nouns) {
        	System.out.println(w.getWord() + " " + w.getCount());
        }*/
        
        /*for (LemmatizerData.WordTag wtag : result.getList()) {
    	System.out.println(wtag.getValue() + " " + wtag.getTag() + " " + wtag.getLemma());
    	}*/


    }
}
