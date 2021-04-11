package common.UObject;

import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;

public class UObjectTest {

    @Test
    public void addTest() throws UObjectException {

        UObject testObj = new UObject();
        ArrayList<String> testSubList = new ArrayList<>();
        testSubList.add("test1");
        testSubList.add("test2");
        testObj.add(testSubList);

        ArrayList<String> actual =  testObj.get(0);

        Assert.assertEquals(testSubList, actual);

    }

    @Test(expected = UObjectException.class)
    public void getNonExistingTest() throws UObjectException {

        UObject testObj = new UObject();
        testObj.get(0);

    }

    @Test
    public void getAllTest() throws UObjectException {

        ArrayList<String> testSubList = new ArrayList<>();
        testSubList.add("test1");
        testSubList.add("test2");
        ArrayList<ArrayList<String>> testList = new ArrayList<>();
        testList.add(testSubList);

        UObject testObj = new UObject();
        testObj.add(testSubList);

        ArrayList<ArrayList<String>> actual =  testObj.getAll();

        Assert.assertEquals(testList, actual);

    }

}
