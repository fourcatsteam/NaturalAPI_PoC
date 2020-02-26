package fourCars.Poc_NaturalAPI_Design;

import java.awt.desktop.UserSessionEvent;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonTypeInfo.As;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import FourCats.Poc_NaturalAPI_Discover.LemmatizerAccess;
import FourCats.Poc_NaturalAPI_Discover.LemmatizerAccessInterface;
import FourCats.Poc_NaturalAPI_Discover.LemmatizerData;
import FourCats.Poc_NaturalAPI_Discover.ParserAccess;
import FourCats.Poc_NaturalAPI_Discover.ParserAccessInterface;
import edu.stanford.nlp.patterns.GetPatternsFromDataMultiClass.Flags;

public class SupportModule {
    public static String getFeatureNameFromGherkin(String gherkinString) {
        //get feature name by picking the text between keywords "Feature:" and "Scenario:"
        int indexFeatureStart = gherkinString.indexOf("Feature:")+9;
        int indexFeatureEnd = gherkinString.indexOf("Scenario")-1;
        return gherkinString.substring(indexFeatureStart,indexFeatureEnd);
    }
    
    public static String getScenarioNameFromGherkin(String gherkinString) {
        //get scenario name by picking the text between first row and "Given:"
        int indexFeatureStart = 1;
        int indexFeatureEnd = -1;
        if (gherkinString.indexOf("As a:")!=-1)
            indexFeatureEnd = gherkinString.indexOf("As a:")-1;
        else
            indexFeatureEnd = gherkinString.indexOf("Given")-1;
        return gherkinString.substring(indexFeatureStart,indexFeatureEnd);
    }
    
    public static String getUserNameFromGherkin(String gherkinString) {
        //get scenario user by picking the text between keywords "As a:" and "Given:"
        //if there isn't the keyword "As as:" return "All"
        int indexUserStart = -1;
        int indexUserEnd = 0;
        if (gherkinString.indexOf("As a:")!=-1) {
            indexUserStart = gherkinString.indexOf("As a:")+5;
            indexUserEnd = gherkinString.indexOf("Given")-1;
            if (indexUserStart <= indexUserEnd)
                return gherkinString.substring(indexUserStart,indexUserEnd);
        }
        return "All";
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
   
   public static List<User> loadScenario(String featurePath) throws IOException {
      String doc = loadFile(featurePath);
      //run document lemmatization
      LemmatizerAccessInterface lemmatizer = new LemmatizerAccess();
      LemmatizerData result = lemmatizer.lemmatizeSentence(doc);
      
      ParserAccessInterface depparser = new ParserAccess();
      List<User> lUsers = null;
      User user = null;
      String[] arrScenarios = doc.split("Scenario:"); //split all scenarios to different strings
      //
      for (String scenario : Arrays.asList(arrScenarios).subList(1, arrScenarios.length)) {
          System.out.println("--------------------------------------GHERKIN SCENARIO: '" + SupportModule.getScenarioNameFromGherkin(scenario) + "'--------------------------------------");
          System.out.println(scenario);
          user = depparser.parseSentence(scenario);
          user.setName(SupportModule.getUserNameFromGherkin(scenario));
          boolean userFound = false; //check if the user is already in the users list (lUsers)
          if (lUsers!=null) {
              for (User u : lUsers) {
                  if (user.getName().equals(u.getName())) {
                      u.addOperations(user.getOperations());
                      userFound = true;
                      break;
                  }
              }
              if(!userFound) 
                  lUsers.add(user);
          }
          else {
              lUsers = new ArrayList<User>();
              lUsers.add(user);
          }
         
      }
           
      System.out.println("Candidate operations for the user '" + SupportModule.getUserNameFromGherkin(doc) + "':" + "\n" + user.toString());
      System.out.println("Candidate parameters for operations: \n" + SupportModule.getParametersFromNouns(result) + "\n");
      
      return lUsers;
       
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
           if (input.equals(""))
               candidatesParameters.add(new Parameter(mainCandidateParam));
           else 
               candidatesParameters.add(new Parameter(mainCandidateParam, input));
           for (Parameter p : candidatesParameters)
               operation.addParameter(p);
       }
       //blackList.addTerm(suggestedOp); 
   }
}
