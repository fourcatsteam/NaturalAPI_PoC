package FourCats.entity;

import java.util.LinkedList;

public class Bdl {
    private LinkedList<String> nouns;
    private LinkedList<String> verbs;
    private LinkedList<String> predicates;

    public Bdl() {
        nouns=new LinkedList<String>();
        verbs=new LinkedList<String>();
        predicates=new LinkedList<String>();
    }
}
