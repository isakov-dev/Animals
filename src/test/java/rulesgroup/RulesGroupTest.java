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
    ArrayList<RuleInterface> rules;
    ArrayList<OperatorInterface> operators;

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

        rules = new ArrayList<>();

        RulesGroup rulesGroup = new RulesGroup(rules);
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

        rules = new ArrayList<>();

        RulesGroup rulesGroup = new RulesGroup(rules);

        try {
            rulesGroup.execute(props);
        } catch (RulesGroupException exception) {
            Assert.assertEquals("Invalid rules", exception.getMessage());
            throw exception;
        }

    }

    @Test
    public void oneRuleTest() throws RulesGroupException {

        rules = new ArrayList<>();
        rules.add(rule1);

        RulesGroup rulesGroup = new RulesGroup(rules);

        boolean result = rulesGroup.execute(props);

        Assert.assertTrue(result);

    }

    @Test (expected = RulesGroupException.class)
    public void oneRuleWithOperatorsTest() throws RulesGroupException {

        rules = new ArrayList<>();
        rules.add(rule1);

        operators = new ArrayList<>();
        operators.add(operatorAnd);
        operators.add(operatorAnd);

        RulesGroup rulesGroup = new RulesGroup(rules, operators);

        try {
            rulesGroup.execute(props);
        } catch (RulesGroupException exception) {
            Assert.assertEquals("Invalid rules", exception.getMessage());
            throw exception;
        }

    }

    @Test
    public void severalRulesOrTest() throws RulesGroupException {

        rules = new ArrayList<>();
        rules.add(rule1);
        rules.add(rule2);
        rules.add(rule3);

        operators = new ArrayList<>();
        operators.add(operatorOr);
        operators.add(operatorOr);

        RulesGroup rulesGroup = new RulesGroup(rules, operators);
        boolean result = rulesGroup.execute(props);

        Assert.assertTrue(result);

    }

    @Test
    public void severalRulesAndTest() throws RulesGroupException {

        rules = new ArrayList<>();
        rules.add(rule1);
        rules.add(rule2);
        rules.add(rule3);

        operators = new ArrayList<>();
        operators.add(operatorAnd);
        operators.add(operatorAnd);

        RulesGroup rulesGroup = new RulesGroup(rules, operators);
        boolean result = rulesGroup.execute(props);

        Assert.assertFalse(result);

    }

    @Test
    public void severalRulesAndOrTest() throws RulesGroupException {

        rules = new ArrayList<>();
        rules.add(rule1);
        rules.add(rule2);
        rules.add(rule3);

        operators = new ArrayList<>();
        operators.add(operatorAnd);
        operators.add(operatorOr);

        RulesGroup rulesGroup = new RulesGroup(rules, operators);
        boolean result = rulesGroup.execute(props);

        Assert.assertTrue(result);

    }

    @Test
    public void severalRulesOrAndTest() throws RulesGroupException {

        rules = new ArrayList<>();
        rules.add(rule1);
        rules.add(rule2);
        rules.add(rule3);

        operators = new ArrayList<>();
        operators.add(operatorOr);
        operators.add(operatorAnd);

        RulesGroup rulesGroup = new RulesGroup(rules, operators);
        boolean result = rulesGroup.execute(props);

        Assert.assertFalse(result);

    }

    @Test (expected = RulesGroupException.class)
    public void severalRulesNotEnoughOperators() throws RulesGroupException {

        rules = new ArrayList<>();
        rules.add(rule1);
        rules.add(rule2);
        rules.add(rule3);

        operators = new ArrayList<>();
        operators.add(operatorOr);

        RulesGroup rulesGroup = new RulesGroup(rules, operators);

        try {
            rulesGroup.execute(props);
        } catch (RulesGroupException exception) {
            Assert.assertEquals("Invalid rules", exception.getMessage());
            throw exception;
        }

    }

    @Test (expected = RulesGroupException.class)
    public void severalRulesTooMuchOperators() throws RulesGroupException {

        rules = new ArrayList<>();
        rules.add(rule1);
        rules.add(rule2);

        operators = new ArrayList<>();
        operators.add(operatorOr);
        operators.add(operatorOr);

        RulesGroup rulesGroup = new RulesGroup(rules, operators);

        try {
            rulesGroup.execute(props);
        } catch (RulesGroupException exception) {
            Assert.assertEquals("Invalid rules", exception.getMessage());
            throw exception;
        }

    }

}