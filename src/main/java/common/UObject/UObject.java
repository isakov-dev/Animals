package common.UObject;

import java.util.ArrayList;

public class UObject implements UObjectInterface {

    private ArrayList<ArrayList<String>> list = new ArrayList<>();

    public void add(ArrayList<String> subList) {
        list.add(subList);
    }

    public ArrayList<String> get(int index) throws UObjectException {

        if (list.size() > index)
            return list.get(index);
        else
            throw new UObjectException("Index is out of range");

    }

}
