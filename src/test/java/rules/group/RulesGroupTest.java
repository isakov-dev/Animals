package rules.group;

import operators.operatorinterface.OperatorInterface;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;
import rules.ruleinterface.RuleInterface;

public class RulesGroupTest {

    RuleInterface rule1, rule2, rule3;
    OperatorInterface operatorOr, operatorAnd;

    @Before
    public void init() {

        rule1 = Mockito.mock(RuleInterface.class);
        Mockito.when(rule1.execute()).thenReturn(true);

        rule2 = Mockito.mock(RuleInterface.class);
        Mockito.when(rule2.execute()).thenReturn(true);

        rule3 = Mockito.mock(RuleInterface.class);
        Mockito.when(rule3.execute()).thenReturn(false);

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
    public void noRulesTest() throws RulesGroupException {
        RulesGroup rulesGroup = new RulesGroup();
        rulesGroup.execute();
    }

    @Test
    public void oneRuleTest() throws RulesGroupException {

        RulesGroup rulesGroup = new RulesGroup();
        rulesGroup.addRule(rule1);

        boolean result = rulesGroup.execute();

        Assert.assertTrue(result);

    }

    @Test (expected = RulesGroupException.class)
    public void oneRuleWithOperatorsTest() throws RulesGroupException {

        RulesGroup rulesGroup = new RulesGroup();

        rulesGroup.addRule(rule1);

        rulesGroup.addOperator(operatorAnd);
        rulesGroup.addOperator(operatorAnd);

        boolean result = rulesGroup.execute();

        Assert.assertTrue(result);

    }

    @Test
    public void severalRulesOrTest() throws RulesGroupException {

        RulesGroup rulesGroup = new RulesGroup();

        rulesGroup.addRule(rule1);
        rulesGroup.addRule(rule2);
        rulesGroup.addRule(rule3);

        rulesGroup.addOperator(operatorOr);
        rulesGroup.addOperator(operatorOr);

        boolean result = rulesGroup.execute();

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

        boolean result = rulesGroup.execute();

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

        boolean result = rulesGroup.execute();

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

        boolean result = rulesGroup.execute();

        Assert.assertFalse(result);

    }

    @Test (expected = RulesGroupException.class)
    public void severalRulesNotEnoughOperators() throws RulesGroupException {

        RulesGroup rulesGroup = new RulesGroup();

        rulesGroup.addRule(rule1);
        rulesGroup.addRule(rule2);
        rulesGroup.addRule(rule3);

        rulesGroup.addOperator(operatorOr);

        rulesGroup.execute();

    }

    @Test (expected = RulesGroupException.class)
    public void severalRulesTooMuchOperators() throws RulesGroupException {

        RulesGroup rulesGroup = new RulesGroup();

        rulesGroup.addRule(rule1);
        rulesGroup.addRule(rule2);

        rulesGroup.addOperator(operatorOr);
        rulesGroup.addOperator(operatorOr);

        rulesGroup.execute();

    }

}