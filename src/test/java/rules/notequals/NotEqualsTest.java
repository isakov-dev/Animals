package rules.notequals;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class NotEqualsTest {

    ArrayList<String> properties;

    @Before
    public void init() {
        properties = new ArrayList<>(Arrays.asList("value1", "value2", "value3"));
    }

    @Test
    public void notContainsTest() {
        NotEquals rule = new NotEquals("value4");
        Assert.assertTrue(rule.execute(properties));
    }

    @Test
    public void containsTest() {
        NotEquals rule = new NotEquals("value2");
        Assert.assertFalse(rule.execute(properties));
    }

}