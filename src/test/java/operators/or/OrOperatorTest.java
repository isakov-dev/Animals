package operators.or;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class OrOperatorTest {

    OrOperator or;

    @Before
    public void init() {
        or = new OrOperator();
    }

    @Test
    public void trueTrueTest() {
        Assert.assertTrue(or.apply(true, true));
    }

    @Test
    public void trueFalseTest() {
        Assert.assertTrue(or.apply(true, false));
    }

    @Test
    public void falseFalseTest() {
        Assert.assertFalse(or.apply(false, false));
    }

}