package main.counter;

import common.uobject.UObject;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import rulesgroup.RulesGroupException;
import rulesgroup.RulesGroupInterface;

import java.util.ArrayList;

public class CounterTest {

    UObject units;
    RulesGroupInterface rulesGroup;

    @Before
    public void init() throws RulesGroupException {

        ArrayList<String> unitOne = new ArrayList<>();
        unitOne.add("value1");

        ArrayList<String> unitTwo = new ArrayList<>();
        unitOne.add("value2");

        units = new UObject();
        units.add(unitOne);
        units.add(unitTwo);
        units.add(unitTwo);
        units.add(unitOne);
        units.add(unitOne);

        rulesGroup = Mockito.mock(RulesGroupInterface.class);
        Mockito.when(rulesGroup.execute(unitOne)).thenReturn(true);
        Mockito.when(rulesGroup.execute(unitTwo)).thenReturn(false);

    }

    @Test
    public void countTest() throws RulesGroupException {

        Counter counter = new Counter(units, rulesGroup);
        int count = counter.count();

        Assert.assertEquals(3, count);

    }

}