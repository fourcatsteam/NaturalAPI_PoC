package fourCars.Poc_NaturalAPI_Design;

import java.util.ArrayList;
import java.util.List;

public class Scenario { 
    private List<Operation> lOperations; 
    private String name;
    private String description;
    
    public Scenario(Operation operation) {
        lOperations = new ArrayList<Operation>();
        lOperations.add(operation);
        this.name = null;
        this.description = null;
    }
    
    public Scenario(List<Operation> operation) {
        lOperations = new ArrayList<Operation>();
        for (Operation o : operation) {
            lOperations.add(o);
        }
        this.name = null;
        this.description = null;
    }
    
    public Scenario() {
        lOperations = new ArrayList<Operation>();
        this.name = null;
        this.description = null;
    }

    public void addOperation(Operation operationToAdd) {
        this.lOperations.add(operationToAdd);
    }
    
    public void setName(String scenarioName) {
        this.name = scenarioName;
    }
    
    public String getName() {
        return name;
    }
    
    public List<Operation> getOperations(){
        return lOperations;
    }
    
    public void setDescription(String scenarioDescription) {
        this.name = scenarioDescription;
    }
    
    public String getDescription() {
        return description;
    }
    
    @Override
    public String toString() {
        String operationStr = "";
        for (Operation operation : lOperations) {
            operationStr += operation.toString()+"\n";
        }
        return operationStr;
    }
}
