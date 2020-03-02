package fourCats.Poc_NaturalAPI_Develop;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        
        System.out.println("Premere 1 per Java, 2 per C++");
        Scanner in = new Scanner(System.in);
        int choice = in.nextInt();
        
        if(choice == 1) {
        	CreateJava java = new CreateJava();
            try {
            	java.create();
            }
            catch(InputMismatchException ie) {
            	ie.printStackTrace();
            }
        }
        else if(choice == 2) {
        	CreateCPP cpp = new CreateCPP(); 
        	cpp.create();
        }
        else {
            in.close();
        	throw new InputMismatchException("Errore: input non valido\nInput ammessi: 1 per Java, 2 per C++");
        }
        in.close();

    }
}
