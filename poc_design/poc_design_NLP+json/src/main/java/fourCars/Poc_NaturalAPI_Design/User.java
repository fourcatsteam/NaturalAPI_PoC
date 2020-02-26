package fourCars.Poc_NaturalAPI_Design;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class User {
    private String name;
    private List<Operation> lOperation;
    
    public User(){
        this.name = null;
        this.lOperation = new ArrayList<Operation>();
    }
    
    public User(String userName){
        this.name = userName;
        this.lOperation = new ArrayList<Operation>();
    }
    
    public void setName(String userName) {
        this.name = userName;
    }
    
    public String getName() {
        return name;
    }
    
   public void addOperation(Operation operationToAdd) {
       this.lOperation.add(operationToAdd);
   }
   
   public void addOperations(List<Operation> operationsToAdd) {
       this.lOperation.addAll(operationsToAdd);
   }
   
   public List<Operation> getOperations() {
       return lOperation;
   }
   
   @Override
   public String toString() {
       String OperationStr = "";
       for (Operation s : lOperation) {
           OperationStr += s.toString();
       }
       return OperationStr;
   }
   
}
