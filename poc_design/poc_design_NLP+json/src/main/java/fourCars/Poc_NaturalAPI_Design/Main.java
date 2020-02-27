package fourCars.Poc_NaturalAPI_Design;

import java.io.IOException;
import java.util.List;


public class Main 
{
    public static void main( String[] args ) throws IOException
    {
        
      List<User> lUsers = SupportModule.loadFeature("txt_documents\\prova.feature");
      BAL bal = new BAL(lUsers);
      SupportModule.createJsonFromBAL(bal, "output.json");
        
    }
}
