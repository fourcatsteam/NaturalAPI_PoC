package fourCars.Poc_NaturalAPI_Design;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import FourCats.Poc_NaturalAPI_Discover.LemmatizerData;

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
   
}
