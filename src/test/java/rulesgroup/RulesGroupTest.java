package rulesgroup;

import operators.operatorinterface.OperatorInterface;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import rules.ruleinterface.RuleInterface;

import java.util.ArrayList;

public class RulesGroupTest {

    ArrayList<String> props;
    RuleInterface rule1, rule2, rule3;
    OperatorInterface operatorOr, operatorAnd;

    @Before
    public void init() {

        props = new ArrayList<>();
        props.add("prop1");
        props.add("prop2");

        rule1 = Mockito.mock(RuleInterface.class);
        Mockito.when(rule1.execute(props)).thenReturn(true);

        rule2 = Mockito.mock(RuleInterface.class);
        Mockito.when(rule2.execute(props)).thenReturn(true);

        rule3 = Mockito.mock(RuleInterface.class);
        Mockito.when(rule3.execute(props)).thenReturn(false);

        operatorOr = Mockito.mock(OperatorInterface.class);
        Mockito.when(operatorOr.apply(true, true)).thenReturn(true);
        Mockito.when(operatorOr.apply(false, true)).thenReturn(true);
        Mockito.when(operatorOr.apply(true, false)).thenReturn(true);
        Mockito.when(operatorOr.apply(false, false)).thenReturn(false);

        operatorAnd = Mockito.mock(OperatorInterface.class);
        Mockito.when(operatorAnd.apply(true, true)).thenReturn(true);
        Mockito.when(operatorAnd.apply(false, true)).thenReturn(false);
        Mockito.when(operatorAnd.apply(true, false)).thenReturn(false);
        Mockito.when(operatorAnd.apply(false, false)).thenReturn(false);

    }

    @Test (expected = RulesGroupException.class)
    public void emptyPropertiesTest() throws RulesGroupException {

        RulesGroup rulesGroup = new RulesGroup();
        ArrayList<String> emptyProps = new ArrayList<>();

        try {
            rulesGroup.execute(emptyProps);
        } catch (RulesGroupException exception) {
            Assert.assertEquals("Empty properties", exception.getMessage());
            throw exception;
        }

    }

    @Test (expected = RulesGroupException.class)
    public void noRulesTest() throws RulesGroupException {

        RulesGroup rulesGroup = new RulesGroup();

        try {
            rulesGroup.execute(props);
        } catch (RulesGroupException exception) {
            Assert.assertEquals("Invalid rules", exception.getMessage());
            throw exception;
        }

    }

    @Test
    public void oneRuleTest() throws RulesGroupException {

        RulesGroup rulesGroup = new RulesGroup();
        rulesGroup.addRule(rule1);

        boolean result = rulesGroup.execute(props);

        Assert.assertTrue(result);

    }

    @Test (expected = RulesGroupException.class)
    public void oneRuleWithOperatorsTest() throws RulesGroupException {

        RulesGroup rulesGroup = new RulesGroup();

        rulesGroup.addRule(rule1);

        rulesGroup.addOperator(operatorAnd);
        rulesGroup.addOperator(operatorAnd);

        try {
            rulesGroup.execute(props);
        } catch (RulesGroupException exception) {
            Assert.assertEquals("Invalid rules", exception.getMessage());
            throw exception;
        }

    }

    @Test
    public void severalRulesOrTest() throws RulesGroupException {

        RulesGroup rulesGroup = new RulesGroup();

        rulesGroup.addRule(rule1);
        rulesGroup.addRule(rule2);
        rulesGroup.addRule(rule3);

        rulesGroup.addOperator(operatorOr);
        rulesGroup.addOperator(operatorOr);

        boolean result = rulesGroup.execute(props);

        Assert.assertTrue(result);

    }

    @Test
    public void severalRulesAndTest() throws RulesGroupException {

        RulesGroup rulesGroup = new RulesGroup();

        rulesGroup.addRule(rule1);
        rulesGroup.addRule(rule2);
        rulesGroup.addRule(rule3);

        rulesGroup.addOperator(operatorAnd);
        rulesGroup.addOperator(operatorAnd);

        boolean result = rulesGroup.execute(props);

        Assert.assertFalse(result);

    }

    @Test
    public void severalRulesAndOrTest() throws RulesGroupException {

        RulesGroup rulesGroup = new RulesGroup();

        rulesGroup.addRule(rule1);
        rulesGroup.addRule(rule2);
        rulesGroup.addRule(rule3);

        rulesGroup.addOperator(operatorAnd);
        rulesGroup.addOperator(operatorOr);

        boolean result = rulesGroup.execute(props);

        Assert.assertTrue(result);

    }

    @Test
    public void severalRulesOrAndTest() throws RulesGroupException {

        RulesGroup rulesGroup = new RulesGroup();

        rulesGroup.addRule(rule1);
        rulesGroup.addRule(rule2);
        rulesGroup.addRule(rule3);

        rulesGroup.addOperator(operatorOr);
        rulesGroup.addOperator(operatorAnd);

        boolean result = rulesGroup.execute(props);

        Assert.assertFalse(result);

    }

    @Test (expected = RulesGroupException.class)
    public void severalRulesNotEnoughOperators() throws RulesGroupException {

        RulesGroup rulesGroup = new RulesGroup();

        rulesGroup.addRule(rule1);
        rulesGroup.addRule(rule2);
        rulesGroup.addRule(rule3);

        rulesGroup.addOperator(operatorOr);

        try {
            rulesGroup.execute(props);
        } catch (RulesGroupException exception) {
            Assert.assertEquals("Invalid rules", exception.getMessage());
            throw exception;
        }

    }

    @Test (expected = RulesGroupException.class)
    public void severalRulesTooMuchOperators() throws RulesGroupException {

        RulesGroup rulesGroup = new RulesGroup();

        rulesGroup.addRule(rule1);
        rulesGroup.addRule(rule2);

        rulesGroup.addOperator(operatorOr);
        rulesGroup.addOperator(operatorOr);

        try {
            rulesGroup.execute(props);
        } catch (RulesGroupException exception) {
            Assert.assertEquals("Invalid rules", exception.getMessage());
            throw exception;
        }

    }

}