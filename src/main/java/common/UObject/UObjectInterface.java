package common.UObject;

public interface UObjectInterface {

    public void set(String key, String value);

    public String get(String key) throws UObjectException;

}
