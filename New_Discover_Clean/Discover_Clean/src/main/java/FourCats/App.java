package FourCats;

import FourCats.interfaceFrameworks.DirectoryAccessBoundary;
import FourCats.usecase.LoadDocument;

import java.io.IOException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws IOException {
        //Creazione dell'access Boundary
        DirectoryAccessBoundary db = new DirectoryAccessBoundary();
        //Inserimento dei TITOLI dei documenti presenti nella folder txt_documents(inserire anche il tipo)
        String r="";
        while(!r.equals("EXIT")){
            //Permette di scegliere il titolo del file
            r=db.chooseFile();
        }
        //Creazione dello use case LoadDocument che permette il caricamento dei documenti
        //Viene passato il DirectAccessBoundary
        LoadDocument ld = new LoadDocument(db);
        //Avviene la creazione dei Documenti dati i titoli
        ld.loadDocs();

        System.out.println(ld.readDocument());




    }
}
