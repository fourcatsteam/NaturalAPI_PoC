package FourCats.Poc_NaturalAPI_Discover;

import java.io.*;
import java.util.Collections;
import java.util.LinkedList;

public class BDL {
	private LinkedList<WordCounter> nouns;
	private LinkedList<WordCounter> verbs;
	private LinkedList<WordCounter> predicates;
	
	public BDL() {
		nouns=new LinkedList<WordCounter>();
		verbs=new LinkedList<WordCounter>();
		predicates=new LinkedList<WordCounter>();
	}
	
	public void addNoun(String noun) {
		Boolean present = false;
		for (WordCounter w : nouns) {
			//controlla se le stringhe sono uguali
			if(w.getWord().equalsIgnoreCase(noun)) {
				w.incrementCounter();
				present = true;
			}
		}
		if(!present) nouns.add(new WordCounter(noun));
	}
	public void addVerb(String verb) {
		Boolean present = false;
		for (WordCounter w : verbs) {
			//controlla se le stringhe sono uguali
			if(w.getWord().equalsIgnoreCase(verb)) {
				w.incrementCounter();
				present = true;
			}
		}
		if(!present) verbs.add(new WordCounter(verb));
		//verbs.add(new WordCounter(verb));
	}
	public void addPredicate(String predicate) {
		Boolean present = false;
		for (WordCounter w : predicates) {
			//controlla se le stringhe sono uguali
			if(w.getWord().equalsIgnoreCase(predicate)) {
				w.incrementCounter();
				present = true;
			}
		}
		if(!present) predicates.add(new WordCounter(predicate));
		//predicates.add(new WordCounter(predicate));
	}
	
	public LinkedList<WordCounter> getNouns(){
		return nouns;
	}

	public void saveToFile(String filename) throws FileNotFoundException {
		saveListToFile(nouns,filename+".nouns.bdl.csv");
		saveListToFile(verbs,filename+".verbs.bdl.csv");
		saveListToFile(predicates,filename+".predicates.bdl.csv");
		//saveNounToFile();
		//saveVerbsToFile();
		//savePredicatesToFile();
	}
	public void useParseData(ParserData data){
		for(String s: data.getList()){
			addPredicate(s);
		}
	}
	public void useLemmatizerData(LemmatizerData res){
		for (LemmatizerData.WordTag wtag : res.getList()) {
			if(wtag.getTag().contains("NN")) {
				addNoun(wtag.getLemma());
			}
			if(wtag.getTag().contains("VB")) {
				addVerb(wtag.getLemma());
			}
		}
	}

	private void saveListToFile(LinkedList<WordCounter> list,String namefile) throws FileNotFoundException {
		PrintWriter writer = new PrintWriter(new File(namefile));
		StringBuilder sb = new StringBuilder();
		for(WordCounter w : list) {
			sb.append(w.getWord());
			sb.append(",");
			sb.append(w.getCount());
			sb.append('\n');
		}
		writer.write(sb.toString());
		writer.close();
	}
	
	//ORDER BDL
	public void order() {
		Collections.sort(nouns,Collections.reverseOrder());
		Collections.sort(verbs,Collections.reverseOrder());
		Collections.sort(predicates,Collections.reverseOrder());
	}

	/*private void saveNounToFile() throws FileNotFoundException{

		PrintWriter writer = new PrintWriter(new File("Project.nouns.bdl.csv"));
		StringBuilder sb = new StringBuilder();
		for(WordCounter w : nouns) {
			sb.append(w.getWord());
			sb.append(",");
			sb.append(w.getCount());
			sb.append('\n');
		}
		writer.write(sb.toString());
		System.out.println("done!");

		/*try {
			BufferedWriter writer = new BufferedWriter(new FileWriter("bld_noun.txt"));
			for(WordCounter w : nouns) {
				writer.write(w.getWord() + " " + w.getCount() + "\n");
			}
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}*/
	
}
