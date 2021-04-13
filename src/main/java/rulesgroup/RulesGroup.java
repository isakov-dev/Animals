package rulesgroup;

import operators.operatorinterface.OperatorInterface;
import rules.ruleinterface.RuleInterface;

import java.util.ArrayList;
import java.util.Iterator;

public class RulesGroup implements RulesGroupInterface {

    private ArrayList<RuleInterface> rules;
    private ArrayList<OperatorInterface> operators = new ArrayList<>();

    public RulesGroup(ArrayList<RuleInterface> rules) {
        this.rules = rules;
    }

    public RulesGroup(ArrayList<RuleInterface> rules, ArrayList<OperatorInterface> operators) {
        this.rules = rules;
        this.operators = operators;
    }

    public boolean execute(ArrayList<String> properties) throws RulesGroupException {

        if (properties.size() > 0) {

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
