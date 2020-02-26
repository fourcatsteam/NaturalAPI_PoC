package FourCats.Poc_NaturalAPI_Discover;


import java.io.FileNotFoundException;
import java.util.LinkedList;

public class App 
{	
    public static void main( String[] args ) throws FileNotFoundException,Exception
    {
    	FileAccessInterface file = new FileAccess();
    	String documento1 = "atm.txt";
    	Document doc = new Document("doc1","Io", 2020,file.readDocument(documento1),"en");
        //System.out.println("---------------------- CONTENUTO DEL DOCUMENTO ----------------------" );
        //System.out.println(doc.getContent());
        
        //Esegue la lemmatization del documento e lo stampa
        LemmatizerAccessInterface lemmatizer = new LemmatizerAccess();
        LemmatizerData result = lemmatizer.lemmatizeSentence(doc.getContent());
        /*for (LemmatizerData.WordTag wtag : result.getList()) {
        	System.out.println(wtag.getValue() + " " + wtag.getTag() + " " + wtag.getLemma());
        }*/

        ParserAccessInterface depparser = new ParserAccess();
        ParserData data = depparser.parseSentence(doc.getContent());

        System.out.println("---------------------- GENERAZIONE BDL ----------------------" );
        BDL bdl = new BDL();
        bdl.useLemmatizerData(result); //Crea dipendenza?
        bdl.useParseData(data);
        bdl.saveToFile("Project");
        System.out.println("BDL generato correttamente");
        
        System.out.println("----------------INTEGRAZIONE BDL----------------------------");
        BDL loadedBdl = file.readBDL("Project");
        String documento2 = "atm_wikipedia.txt";
        Document otherDoc = new Document("doc2","Io",2020,file.readDocument(documento2),"en");
        loadedBdl.useLemmatizerData(lemmatizer.lemmatizeSentence(otherDoc.getContent()));
        loadedBdl.useParseData(depparser.parseSentence(otherDoc.getContent()));
        loadedBdl.saveToFile("Project_with_integration");
        System.out.println("BDL integrato correttamente");
        /*LinkedList<WordCounter> nouns = bdl.getNouns();
        for(WordCounter w : nouns) {
        	System.out.println(w.getWord() + " " + w.getCount());
        }*/


    }
}
