package rules.equals;

import rules.ruleinterface.RuleInterface;

import java.util.ArrayList;

public class Equals implements RuleInterface {

    private ArrayList<String> valuesList;
    private String needle;

    public Equals(ArrayList<String> valuesList, String needle) {
        this.valuesList = valuesList;
        this.needle = needle;
    }

    public boolean execute() {
        return this.valuesList.contains(this.needle);
    }

}
