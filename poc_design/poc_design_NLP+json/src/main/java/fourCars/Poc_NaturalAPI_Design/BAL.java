package fourCars.Poc_NaturalAPI_Design;

import java.util.ArrayList;
import java.util.List;

public class BAL{
    private List<Feature> lFeatures;
    
    public BAL() {
        lFeatures = new ArrayList<Feature>();
    }
    
    public BAL(List<Feature> featuresList) {
        this.lFeatures = featuresList;
    }
    
    public void addFeatureToBAL(Feature featureToAdd) {
        lFeatures.add(featureToAdd);
    }
    
    public List<Feature> getFeatures() {
        return lFeatures;
    }
    
    @Override
    public String toString() {
        String BALStr = "";
        for (Feature f : lFeatures) {
            BALStr +=f.toString();
        }
        return BALStr;
    }
}


