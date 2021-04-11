package common.UObject;

import java.util.ArrayList;

public interface UObjectInterface {

    public void add(ArrayList<String> subList);

    public ArrayList<String> get(int index) throws UObjectException;

    public ArrayList<ArrayList<String>> getAll();

}
