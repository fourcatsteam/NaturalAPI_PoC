package FourCats.interfaceFrameworks;

import FourCats.entity.Document;
import FourCats.entity.Documents;
import FourCats.frameworks.Repository;
import FourCats.interfaceAccess.RepositoryAccess;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.LinkedList;

public class RepositoryBoundary implements RepositoryAccess {

    public Documents read(){
        Documents d = new Documents();
        for(int i=0;i<rep.getNumberTitle();i++){

        }
    }
    public String read(String filename) throws FileNotFoundException {
        String filepath = "txt_documents/" + filename;
        String s, fileContent = new String();
        try{
            BufferedReader input = new BufferedReader(new FileReader(filepath));
            while((s=input.readLine()) != null){
                fileContent += s + " ";
            }
            input.close();

        }catch(IOException e){
            //modificare gestione eccezioni
            fileContent="file non trovato";
        }
        return fileContent;
    }
}
