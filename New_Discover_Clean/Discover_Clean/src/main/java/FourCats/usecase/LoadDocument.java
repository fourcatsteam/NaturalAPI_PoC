package FourCats.usecase;

import FourCats.entity.Document;
import FourCats.entity.Documents;
import FourCats.interfaceAccess.RepositoryAccess;
import com.sun.org.omg.CORBA.Repository;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

/* Use case Interactor per il caricamento dei documenti dalla repository  */
public class LoadDocument{

   private Documents list;
   private RepositoryAccess ra;

    public LoadDocument(RepositoryAccess r){
        list = new Documents();
        ra = r;
    }

    public void loadDocs(){
        ra.read();
    }


}
