package rules.equals;

import rules.ruleinterface.RuleInterface;

import java.util.ArrayList;

public class Equals implements RuleInterface {

    private String needle;

    public Equals(String needle) {
        this.needle = needle;
    }

    public boolean execute(ArrayList<String> valuesList) {
        return valuesList.contains(this.needle);
    }

}
