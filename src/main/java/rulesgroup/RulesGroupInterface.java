package rulesgroup;

import operators.operatorinterface.OperatorInterface;
import rules.ruleinterface.RuleInterface;

import java.util.ArrayList;

public interface RulesGroupInterface {

    void addRule(RuleInterface rule);

    void addOperator(OperatorInterface operator);

    boolean execute(ArrayList<String> properties) throws RulesGroupException;

}
