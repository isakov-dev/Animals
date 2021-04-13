package rules.notequals;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class NotEqualsTest {

    ArrayList<String> values;

    @Before
    public void init() {
        values = new ArrayList<>(Arrays.asList("value1", "value2", "value3"));
    }

    @Test
    public void notContainsTest() {
        NotEquals rule = new NotEquals(values, "value4");
        Assert.assertTrue(rule.execute());
    }

    @Test
    public void containsTest() {
        NotEquals rule = new NotEquals(values, "value2");
        Assert.assertFalse(rule.execute());
    }

}