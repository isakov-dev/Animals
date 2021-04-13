package rulesgroup;

import operators.operatorinterface.OperatorInterface;
import rules.ruleinterface.RuleInterface;

import java.util.ArrayList;
import java.util.Iterator;

public class RulesGroup implements RulesGroupInterface {

    private ArrayList<String> properties;
    private ArrayList<RuleInterface> rules = new ArrayList<>();
    private ArrayList<OperatorInterface> operators = new ArrayList<>();

    public void setProperties(ArrayList<String> properties) {
        this.properties = properties;
    }

    public void addRule(RuleInterface rule) {
        rules.add(rule);
    }

    public void addOperator(OperatorInterface operator) {
        operators.add(operator);
    }

    public boolean execute() throws RulesGroupException {

        if (properties != null && properties.size() > 0) {

            if (rules.size() > 0 && rules.size() == (operators.size() + 1)) {

                if (rules.size() > 1) {

                    ArrayList<Boolean> rulesResults = new ArrayList<>();

                    Iterator<RuleInterface> rulesIterator = this.rules.iterator();
                    while (rulesIterator.hasNext()) {
                        rulesResults.add(rulesIterator.next().execute(properties));
                    }

                    boolean result = false;

                    for (int operatorIndex = 0; operatorIndex < operators.size(); operatorIndex ++) {

                        if (operatorIndex == 0)
                            result = operators.get(operatorIndex).apply(rulesResults.get(operatorIndex),
                                    rulesResults.get(operatorIndex+1));
                        else
                            result = operators.get(operatorIndex).apply(result, rulesResults.get(operatorIndex+1));

                    }

                    return result;

                } else
                    return rules.get(0).execute(properties);

            } else
                throw new RulesGroupException("Invalid rules");

        } else
            throw new RulesGroupException("Empty properties");

    }

}
