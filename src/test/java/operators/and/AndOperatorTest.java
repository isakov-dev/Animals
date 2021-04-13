package operators.and;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class AndOperatorTest {

    AndOperator and;

    @Before
    public void init() {
        and = new AndOperator();
    }

    @Test
    public void trueTrueTest() {
        Assert.assertTrue(and.apply(true, true));
    }

    @Test
    public void trueFalseTest() {
        Assert.assertFalse(and.apply(true, false));
    }

    @Test
    public void falseFalseTest() {
        Assert.assertFalse(and.apply(false, false));
    }

}