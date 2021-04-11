package common.UObject;

import org.junit.Assert;
import org.junit.Test;

public class UObjectTest {

    @Test
    public void addTest() {

        UObject testObj = new UObject();
        testObj.set("field", "value");
        String actual = null;

        try {
            actual = testObj.get("field");
        } catch (UObjectException e) {
            e.printStackTrace();
        }

        Assert.assertEquals("value", actual);

    }

    @Test(expected=UObjectException.class)
    public void getNonExistingTest() throws UObjectException {

        UObject testObj = new UObject();
        testObj.get("field");

    }

}