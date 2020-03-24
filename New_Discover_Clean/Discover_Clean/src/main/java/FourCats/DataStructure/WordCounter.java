package FourCats.DataStructure;

public class WordCounter implements Comparable<WordCounter>{
    private String word;
    private Integer count;

    public WordCounter(String w, Integer c) {
        word=w;
        count=c;
    }

    public WordCounter(String w) {
        this(w,1);
    }

    public void incrementCounter() {
        count++;
    }

    public String getWord() {return word;}
    public Integer getCount() {return count;}

    public int compareTo(WordCounter w) {
        if(count<w.count) return -1;
        if(count==w.count) return 0;
        return 1;
    }
}
