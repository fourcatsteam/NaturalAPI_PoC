package fourCars.Poc_NaturalAPI_Design;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.protobuf.ByteString.Output;

import FourCats.Poc_NaturalAPI_Discover.LemmatizerAccess;
import FourCats.Poc_NaturalAPI_Discover.LemmatizerAccessInterface;
import FourCats.Poc_NaturalAPI_Discover.LemmatizerData;
import FourCats.Poc_NaturalAPI_Discover.ParserAccess;
import FourCats.Poc_NaturalAPI_Discover.ParserAccessInterface;
import edu.stanford.nlp.io.EncodingPrintWriter.out;
import edu.stanford.nlp.parser.lexparser.Item;


public class Main 
{
    public static void main( String[] args ) throws IOException
    {
      String doc = null;
      doc = SupportModule.loadFile("txt_documents\\prova.feature");
      
    //Separa il contenuto del document quando trova un "."
      String[] sentences = doc.split("\\.");
      
      //Esegue la lemmatization del documento e lo stampa
      LemmatizerAccessInterface lemmatizer = new LemmatizerAccess();
      LemmatizerData result = lemmatizer.lemmatizeSentence(doc);
      
      ParserAccessInterface depparser = new ParserAccess();
      Feature feature = depparser.parseSentence(doc);
      feature.setName(SupportModule.getFeatureNameFromGherkin(doc));
 
      //assigns name to scenarios
      for(Scenario s: feature.getScenarios()) {
          s.setName(SupportModule.getScenarioNameFromGherkin(doc));
      }
      
     
         
      System.out.println("Candidate operations for the Feature '" + SupportModule.getFeatureNameFromGherkin(doc) + "':" + "\n" + feature.toString());
      System.out.println("Candidate parameters for operations: \n" + SupportModule.getParametersFromNouns(result) + "\n");
         
      
     
      BAL bal = new BAL(Arrays.asList(feature));
    //Creating the ObjectMapper object
      ObjectMapper mapper = new ObjectMapper();
      //Converting the Object to JSONString
      String jsonString;
      try {
          jsonString = mapper.writeValueAsString(bal);
          System.out.println(jsonString);
          mapper.writeValue(new File("output.json"), bal);
          System.out.println("\n File json creato!");
      } catch (JsonProcessingException e) {
          // TODO Auto-generated catch block
          e.printStackTrace();
      }
        
    }
}
