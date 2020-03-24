package FourCats.usecase;
import FourCats.DataStructure.LemmatizerData;
import FourCats.entity.Bdl;
import FourCats.entity.Document;
import FourCats.entity.Documents;

import java.io.FileNotFoundException;

public class BdlGenerator {
    Documents listDocumentsLoaded;
    DocumentParser dp;
    Bdl bdl;

    public BdlGenerator(Documents list,DocumentParser d){
        listDocumentsLoaded = list;
        dp = d;
        bdl = new Bdl();
    }

    public Bdl generateBDL(){
        for(Document d: listDocumentsLoaded.getList()){
            for(String s: dp.parseSentence(d.getContent())){
                bdl.addPredicate(s);
            }
            for(LemmatizerData.WordTag wtag : dp.lemmatizeSentence(d.getContent()).getList()){
                if(wtag.getTag().contains("NN")) {
                    bdl.addNoun(wtag.getLemma());
                }
                if(wtag.getTag().contains("VB")) {
                    bdl.addVerb(wtag.getLemma());
                }
            }
            bdl.order();
        }
        return bdl;
    }

    public void saveBdlToFile(String nameProject) throws FileNotFoundException {
        bdl.saveToFile(nameProject);
    }



}
