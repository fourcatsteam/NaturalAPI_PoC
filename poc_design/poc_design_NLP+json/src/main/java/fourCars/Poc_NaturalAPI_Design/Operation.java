package fourCars.Poc_NaturalAPI_Design;

import java.util.ArrayList;
import java.util.List;

public class Operation {
    private String name;
    private List<Parameter> param;
    
    public Operation() {
        this.name = null;
        this.param = new ArrayList<Parameter>();
    }
    public Operation(String operationName) {
        this.name = operationName;
        this.param = new ArrayList<Parameter>();
    }
    
    public void setName(String operationName) {
        this.name = operationName;
    }
    
    public String getName() {
        return name;
    }
    
    public void addParameterName(String paramName) {
        Parameter par = new Parameter();
        par.setName(paramName);
        this.param.add(par);
    }
    
    public void updateParameterName(String paramName, String newName) {
        for (Parameter p : param) {
            if (p.getName()==paramName) {
                p.setName(newName);
                return;
            }
        }
    }
    
    public void updateParameterType(String paramName, String newType) {
        for (Parameter p : param) {
            if (p.getName()==paramName) {
                p.setType(newType);
                return;
            }
        }
    }
    
    public void updateIsParameterRequired(String paramName, boolean isRequired) {
        for (Parameter p : param) {
            if (p.getName()==paramName) {
                p.setRequired(isRequired);
                return;
            }
        }
    }
    
    public List<Parameter> getParameters() {
        return param;
    }
    
    @Override
    public String toString() {
        return "opearationId:" + name + " parameters: " + param.toString();
    }
    
    
}
