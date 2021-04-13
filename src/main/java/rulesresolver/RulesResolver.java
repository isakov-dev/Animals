package rulesresolver;

import common.uobject.UObjectException;
import common.uobject.UObjectInterface;
import rules.ruleinterface.RuleInterface;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;

public class RulesResolver {

    private HashMap<String, Class> rulesMap;
    private UObjectInterface rulesAndOperatorsArray;
    private ArrayList<RuleInterface> rulesList = new ArrayList<>();

    public RulesResolver(HashMap<String, Class> rulesMap, UObjectInterface rulesAndOperatorsArray) {
        this.rulesMap = rulesMap;
        this.rulesAndOperatorsArray = rulesAndOperatorsArray;
    }

    public ArrayList<RuleInterface> resolve () throws RulesResolverException {

        if (rulesAndOperatorsArray.getAll().size() > 1) {

            for (int ruleIndex = 0; ruleIndex < rulesAndOperatorsArray.getAll().size() - 1; ruleIndex ++) {

                try {

                    ArrayList<String> ruleArray = rulesAndOperatorsArray.get(ruleIndex);

                    RuleInterface rule = (RuleInterface) rulesMap.get(ruleArray.get(0)).getConstructor(String.class)
                            .newInstance(ruleArray.get(1));
                    rulesList.add(rule);

                } catch (UObjectException e) {
                    throw new RulesResolverException("Rules data are not valid");
                } catch (InvocationTargetException | InstantiationException | NoSuchMethodException | IllegalAccessException e) {
                    throw new RulesResolverException("Cannot create object of rule class");
                }

            }

            return rulesList;

        } else if (rulesAndOperatorsArray.getAll().size() == 1) {

            try {

                ArrayList<String> ruleArray = rulesAndOperatorsArray.get(0);

                if (rulesMap.containsKey(ruleArray.get(0))) {

                    RuleInterface rule = (RuleInterface) rulesMap.get(ruleArray.get(0)).getConstructor(String.class)
                            .newInstance(ruleArray.get(1));
                    rulesList.add(rule);

                    return rulesList;

                } else
                    throw new RulesResolverException("Cannot resolve rule");

            } catch (UObjectException e) {
                throw new RulesResolverException("Rules data are not valid");
            } catch (InvocationTargetException | InstantiationException | NoSuchMethodException | IllegalAccessException e) {
                throw new RulesResolverException("Cannot create object of rule class");
            }

        } else
            throw new RulesResolverException("No rules to resolve");

    }

}
