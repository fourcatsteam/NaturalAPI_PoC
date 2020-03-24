package FourCats;

import FourCats.entity.Bdl;
import FourCats.interfaceFrameworks.DirectoryAccessBoundary;
import FourCats.interfaceFrameworks.ParserAccessBoundary;
import FourCats.usecase.BdlGenerator;
import FourCats.usecase.DocumentParser;
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


        ParserAccessBoundary pab = new ParserAccessBoundary();
        DocumentParser dp = new DocumentParser(pab);
        BdlGenerator gen = new BdlGenerator(ld.getList(),dp);
        Bdl createdBdl = gen.generateBDL();
        gen.saveBdlToFile("provaBdl");




    }
}
