package rules.notequals;

import rules.ruleinterface.RuleInterface;

import java.util.ArrayList;

public class NotEquals implements RuleInterface {

    private ArrayList<String> valuesList;
    private String needle;

    public NotEquals(ArrayList<String> valuesList, String needle) {
        this.valuesList = valuesList;
        this.needle = needle;
    }

    public boolean execute() {
        return !this.valuesList.contains(this.needle);
    }

}
