package fourCars.Poc_NaturalAPI_Design;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import FourCats.Poc_NaturalAPI_Discover.LemmatizerAccess;
import FourCats.Poc_NaturalAPI_Discover.LemmatizerAccessInterface;
import FourCats.Poc_NaturalAPI_Discover.LemmatizerData;
import FourCats.Poc_NaturalAPI_Discover.ParserAccess;
import FourCats.Poc_NaturalAPI_Discover.ParserAccessInterface;

public class SupportModule {
    public static String getFeatureNameFromGherkin(String gherkinString) {
        //get feature name by picking the text between keywords "Feature:" and "Scenario:"
        int indexFeatureStart = gherkinString.indexOf("Feature:")+9;
        int indexFeatureEnd = gherkinString.indexOf("Scenario")-1;
        return gherkinString.substring(indexFeatureStart,indexFeatureEnd);
    }
    
    public static String getScenarioNameFromGherkin(String gherkinString) {
        //get scenario name by picking the text between keywords "Scenario:" and "Given:"
        int indexFeatureStart = gherkinString.indexOf("Scenario:")+10;
        int indexFeatureEnd = gherkinString.indexOf("Given")-1;
        return gherkinString.substring(indexFeatureStart,indexFeatureEnd);
    }
   
    
    public static List<Parameter> getParametersFromNouns(LemmatizerData lemData) {
        BlackList blackList = new BlackList();
        List<Parameter> lParameters = new ArrayList<Parameter>();
        for (LemmatizerData.WordTag wtag : lemData.getList()) {
            if(wtag.getTag().contains("NN") && !blackList.contains(wtag.getLemma())) {
                lParameters.add(new Parameter(wtag.getLemma()));  
                blackList.addTerm(wtag.getLemma());
            }
        }
        return lParameters;
    }
    
    
    
   public static String loadFile(String filename) throws IOException {
       BufferedReader br = new BufferedReader(new FileReader(filename));
       String doc = null;
       try {
           StringBuilder sb = new StringBuilder();
           String line = br.readLine();
           while (line != null) {
               sb.append(line);
               sb.append("\n");
               line = br.readLine();
           }
           doc = sb.toString();
          }
       catch(Exception ioe) {
           System.out.println("error");
       }
       finally {
           br.close();
       }
      return doc;
   }
   
   public static Feature loadScenario(String scenarioPath) throws IOException {
      String doc = loadFile(scenarioPath);
      
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
      
      return feature;
       
   }
   
   public static void createJsonFromBAL(BAL bal, String outputPath) throws IOException {
       //Creating the ObjectMapper object
         ObjectMapper mapper = new ObjectMapper();
         mapper.enable(SerializationFeature.INDENT_OUTPUT);
         //Converting the Object to JSONString
         String jsonString;
         try {
             jsonString = mapper.writeValueAsString(bal);
             System.out.println(jsonString);
             mapper.writeValue(new File(outputPath), bal);
             System.out.println("\n File json creato!");
         } catch (JsonProcessingException e) {
             // TODO Auto-generated catch block
             e.printStackTrace();
         }
   }
   
   public static String extractParameterFromOperationName(Operation operation) {
       int indexFeatureStart = operation.getName().indexOf("_")+1;
       return operation.getName().substring(indexFeatureStart);
   }
   
   public static void suggestParameter(Operation operation) throws IOException {
       List<Parameter> candidatesParameters = new ArrayList<Parameter>();
       String mainCandidateParam = extractParameterFromOperationName(operation);
       System.out.println("--------------------------------------PARAMETER SUGGESTION FOR '" + operation.getName() + "'--------------------------------------");
       System.out.println("Would you like to add '" + mainCandidateParam + "' as a parameter? 1. YES, 2. NO\n");
       BufferedReader reader =
               new BufferedReader(new InputStreamReader(System.in));
       String input = reader.readLine();
       if (input.equals("1")) {
           System.out.println("Please, insert the type for the parameter '" + mainCandidateParam +  "': (void, string, int, bool, double, float...)" );
           System.out.println("Otherwise, press the enter key.\n" );
           input = reader.readLine(); //param type
           candidatesParameters.add(new Parameter(mainCandidateParam, input));
           for (Parameter p : candidatesParameters)
               operation.addParameter(p);
       }
       //blackList.addTerm(suggestedOp); 
   }
}
