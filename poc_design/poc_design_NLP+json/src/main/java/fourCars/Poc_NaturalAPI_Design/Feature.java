package fourCars.Poc_NaturalAPI_Design;

import java.util.ArrayList;
import java.util.List;

public class Feature {
    private String name;
    private List<Scenario> lScenario;
    
    public Feature(){
        this.name = null;
        this.lScenario = new ArrayList<Scenario>();
    }
    
    public Feature(String featureName){
        this.name = featureName;
        this.lScenario = new ArrayList<Scenario>();
    }
    
    public void setName(String featureName) {
        this.name = featureName;
    }
    
    public String getName() {
        return name;
    }
    
   public void addScenario(Scenario scenarioToAdd) {
       this.lScenario.add(scenarioToAdd);
   }
   
   public List<Scenario> getScenarios() {
       return lScenario;
   }
   
   @Override
   public String toString() {
       String scenarioStr = "";
       for (Scenario s : lScenario) {
           scenarioStr += s.toString();
       }
       return scenarioStr;
   }
   
}
