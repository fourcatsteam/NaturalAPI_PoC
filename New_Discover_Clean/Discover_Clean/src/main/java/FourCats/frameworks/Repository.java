package FourCats.frameworks;
import FourCats.entity.Document;

import java.util.LinkedList;

public class Repository{
    LinkedList<String> titleDocumentList;

    public Repository(){
        titleDocumentList = new LinkedList<String>();
    }

    public void addTitleDocument(String d){
        titleDocumentList.add(d);
    }

    public LinkedList<String> getList(){
        return titleDocumentList;
    }

    public int getNumberTitle(){
        return titleDocumentList.size();
    }

}
