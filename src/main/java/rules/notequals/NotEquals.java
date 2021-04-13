package rules.notequals;

import rules.ruleinterface.RuleInterface;

import java.util.ArrayList;

public class NotEquals implements RuleInterface {

    private String needle;

    public NotEquals(String needle) {
        this.needle = needle;
    }

    public boolean execute(ArrayList<String> valuesList) {
        return !valuesList.contains(this.needle);
    }

}
