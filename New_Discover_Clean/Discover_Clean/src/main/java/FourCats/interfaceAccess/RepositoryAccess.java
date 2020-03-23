package FourCats.interfaceAccess;

import java.io.FileNotFoundException;

public interface RepositoryAccess {
    public String read(String filename) throws FileNotFoundException;

}
