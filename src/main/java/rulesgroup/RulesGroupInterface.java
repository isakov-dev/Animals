package rulesgroup;

import operators.operatorinterface.OperatorInterface;
import rules.ruleinterface.RuleInterface;

import java.util.ArrayList;

public interface RulesGroupInterface {

    public void setProperties(ArrayList<String> properties);

    public void addRule(RuleInterface rule);

    public void addOperator(OperatorInterface operator);

    public boolean execute() throws RulesGroupException;

}
