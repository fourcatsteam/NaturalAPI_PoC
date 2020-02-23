package FourCats.Poc_NaturalAPI_Discover;

import java.util.LinkedList;
import java.util.List;

public class LemmatizerData {
	class WordTag{
		private String value;
		private String tag;
		private String lemma;

		public WordTag(String w, String t, String l) {
			value=w;
			tag=t;
			lemma=l;
		}

		public String getValue() {return value;}
		public String getTag() {return tag;}
		public String getLemma() {return lemma;}
	}

	private List<WordTag> lemmatizationResult;
	private List<String> predicateResult;
	
	public LemmatizerData() {
		lemmatizationResult = new LinkedList<WordTag>();
		predicateResult = new LinkedList<String>();
	}
	
	public void addElement(String word, String tag, String lemma) {
		lemmatizationResult.add(new WordTag(word,tag,lemma));
	}
	public void addPredicate(String p){ predicateResult.add(p);}
	
	public List<WordTag> getList() {
		return lemmatizationResult;
	};
	public List<String> getPredicate(){ return predicateResult; }
}
