package rules.equals;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class EqualsTest {

    ArrayList<String> values;

    @Before
    public void init() {
        values = new ArrayList<>(Arrays.asList("value1", "value2", "value3"));
    }

    @Test
    public void containsTest() {
        Equals rule = new Equals(values, "value2");
        Assert.assertTrue(rule.execute());
    }

    @Test
    public void notContainsTest() {
        Equals rule = new Equals(values, "value4");
        Assert.assertFalse(rule.execute());
    }

}