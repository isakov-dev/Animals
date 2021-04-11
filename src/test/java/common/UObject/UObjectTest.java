package common.UObject;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class UObjectTest {

    @Test
    public void addTest() {

        UObject testObj = new UObject();
        ArrayList<String> testSubList = new ArrayList<>();
        testSubList.add("test1");
        testSubList.add("test2");
        testObj.add(testSubList);
        ArrayList<String> actual = null;

        try {
            actual = testObj.get(0);
        } catch (UObjectException e) {
            e.printStackTrace();
        }

        Assert.assertEquals(testSubList, actual);

    }

    @Test(expected=UObjectException.class)
    public void getNonExistingTest() throws UObjectException {

        UObject testObj = new UObject();
        testObj.get(0);

    }

}