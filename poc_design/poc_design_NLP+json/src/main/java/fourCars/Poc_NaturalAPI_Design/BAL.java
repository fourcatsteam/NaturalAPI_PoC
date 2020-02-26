package fourCars.Poc_NaturalAPI_Design;

import java.util.ArrayList;
import java.util.List;

public class BAL{
    private List<User> lUsers;
    
    public BAL() {
        lUsers = new ArrayList<User>();
    }
    
    public BAL(List<User> usersList) {
        this.lUsers = usersList;
    }
    
    public void addUserToBAL(User userToAdd) {
        lUsers.add(userToAdd);
    }
    
    public List<User> getUsers() {
        return lUsers;
    }
    
    @Override
    public String toString() {
        String BALStr = "";
        for (User f : lUsers) {
            BALStr +=f.toString();
        }
        return BALStr;
    }
}


