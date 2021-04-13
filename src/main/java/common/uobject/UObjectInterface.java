package common.uobject;

import java.util.ArrayList;

public interface UObjectInterface {

    void add(ArrayList<String> subList);

    ArrayList<String> get(int index) throws UObjectException;

    ArrayList<ArrayList<String>> getAll();

}
