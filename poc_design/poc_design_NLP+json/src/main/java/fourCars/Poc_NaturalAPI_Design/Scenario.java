package fourCars.Poc_NaturalAPI_Design;

import java.util.ArrayList;
import java.util.List;

public class Scenario { //HA SENSO LASCIARE LO SCENARIO PER TRASFORMAZIONE IN JSON? UTILE?
    private List<Operation> lOperations; 
    private String name;
    
    public Scenario(Operation operation) {
        lOperations = new ArrayList<Operation>();
        lOperations.add(operation);
    }
    
    public Scenario() {
        lOperations = new ArrayList<Operation>();
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
    
    @Override
    public String toString() {
        String operationStr = "";
        for (Operation operation : lOperations) {
            operationStr += operation.toString()+"\n";
        }
        return operationStr;
    }
}
