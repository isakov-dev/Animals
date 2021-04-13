package rulesgroup;

import operators.operatorinterface.OperatorInterface;
import rules.ruleinterface.RuleInterface;

import java.util.ArrayList;

public interface RulesGroupInterface {

    void setProperties(ArrayList<String> properties);

    void addRule(RuleInterface rule);

    void addOperator(OperatorInterface operator);

    boolean execute() throws RulesGroupException;

}
