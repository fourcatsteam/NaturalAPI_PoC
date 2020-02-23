package fouCats.Poc_NaturalAPI_Design;

import java.io.FileWriter;
import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        createJSON json = new createJSON();
        try {
        	FileWriter file = new FileWriter("json.txt");
        	file.write(json.getJa().toString(4));
        	file.close();
        }
        catch(IOException ioe) {
        	System.out.println("errore");
        }
    }
}
