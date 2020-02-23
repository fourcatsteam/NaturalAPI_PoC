package fourCars.Poc_NaturalAPI_Design;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import FourCats.Poc_NaturalAPI_Discover.LemmatizerAccess;
import FourCats.Poc_NaturalAPI_Discover.LemmatizerAccessInterface;
import FourCats.Poc_NaturalAPI_Discover.LemmatizerData;
import FourCats.Poc_NaturalAPI_Discover.ParserAccess;
import FourCats.Poc_NaturalAPI_Discover.ParserAccessInterface;


public class Main 
{
    public static void main( String[] args ) throws IOException
    {
        
      Feature feature = SupportModule.loadScenario("txt_documents\\prova.feature");
      BAL bal = new BAL(Arrays.asList(feature));
      SupportModule.createJsonFromBAL(bal, "output.json");
        
    }
}
