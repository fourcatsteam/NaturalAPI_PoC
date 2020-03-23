package FourCats;

import FourCats.entity.Document;
import FourCats.entity.Documents;
import FourCats.frameworks.Repository;
import FourCats.interfaceFrameworks.RepositoryBoundary;
import FourCats.usecase.LoadDocument;

import java.io.FileNotFoundException;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws FileNotFoundException {
        Repository rep = new Repository();
        rep.addTitleDocument("prova.txt");
        //Aggiunta di altri titoli nel repository
        RepositoryBoundary rb = new RepositoryBoundary();
       LoadDocument ld = new LoadDocument(rb);
       ld.loadDocs();




    }
}
