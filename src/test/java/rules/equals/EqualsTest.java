package rules.equals;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class EqualsTest {

    ArrayList<String> properties;

    @Before
    public void init() {
        properties = new ArrayList<>(Arrays.asList("value1", "value2", "value3"));
    }

    @Test
    public void containsTest() {
        Equals rule = new Equals("value2");
        Assert.assertTrue(rule.execute(properties));
    }

    @Test
    public void notContainsTest() {
        Equals rule = new Equals("value4");
        Assert.assertFalse(rule.execute(properties));
    }

}