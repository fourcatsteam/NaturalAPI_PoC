package FourCats.entity;

import java.util.LinkedList;

public class Documents {
    private LinkedList<Document> list;

    public Documents(){
        list = new LinkedList<Document>();
    }

    public void addByTitle(String t){
        list.add(new Document(t,"autore",2019,"","eng"));
    }

    public void add(Document d){
        list.add(d);
    }
}
