package common.UObject;

import java.util.HashMap;

public class UObject implements UObjectInterface {

    private HashMap<String, String> map = new HashMap<>();

    public void set(String key, String value) {
        map.put(key, value);
    }

    public String get(String key) throws UObjectException {

        if (map.containsKey(key))
            return map.get(key);
        else
            throw new UObjectException("No such key: " + key);

    }

}
