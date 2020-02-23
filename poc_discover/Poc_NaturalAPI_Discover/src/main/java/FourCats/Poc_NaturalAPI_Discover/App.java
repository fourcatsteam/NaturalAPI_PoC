package FourCats.Poc_NaturalAPI_Discover;

import edu.stanford.nlp.ie.util.RelationTriple;
import edu.stanford.nlp.io.IOUtils;
import edu.stanford.nlp.ling.CoreAnnotations;
import edu.stanford.nlp.naturalli.NaturalLogicAnnotations;
import edu.stanford.nlp.naturalli.OpenIE;
import edu.stanford.nlp.naturalli.SentenceFragment;
import edu.stanford.nlp.pipeline.Annotation;
import edu.stanford.nlp.pipeline.StanfordCoreNLP;
import edu.stanford.nlp.semgraph.SemanticGraph;
import edu.stanford.nlp.semgraph.SemanticGraphCoreAnnotations;
import edu.stanford.nlp.util.CoreMap;
import edu.stanford.nlp.util.PropertiesUtils;

import java.io.FileNotFoundException;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Properties;

/**
 * Hello world!
 *
 */
public class App 
{	
    public static void main( String[] args ) throws FileNotFoundException,Exception
    {
    	FileAccessInterface file = new FileAccess();
    	String documento1 = "atm.txt";
    	Document doc = new Document("prova","Io", 2020,file.readDocument(documento1),"it");
    	
        System.out.println(doc.getContent());
        
        //Separa il contenuto del document quando trova un "."
        //String[] sentences = doc.getContent().split("\\.");
        
        //Esegue la lemmatization del documento e lo stampa
        LemmatizerAccessInterface lemmatizer = new LemmatizerAccess();
        LemmatizerData result = lemmatizer.lemmatizeSentence(doc.getContent());
        for (LemmatizerData.WordTag wtag : result.getList()) {
        	System.out.println(wtag.getValue() + " " + wtag.getTag() + " " + wtag.getLemma());
        }

        System.out.println("---------------------- GENERAZIONE BDL ----------------------" );

        BDL bdl = new BDL();
        bdl.useLemmatizerData(result); //Crea dipendenza?
        bdl.saveToFile();


        /*LinkedList<WordCounter> nouns = bdl.getNouns();
        for(WordCounter w : nouns) {
        	System.out.println(w.getWord() + " " + w.getCount());
        }*/


    }
}
