package operatorsresolver;

import common.uobject.UObjectException;
import common.uobject.UObjectInterface;
import operators.operatorinterface.OperatorInterface;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

public class OperatorsResolver {

    private HashMap<String, Class> operatorsMap;
    private UObjectInterface rulesAndOperatorsArray;
    private ArrayList<OperatorInterface> operatorsList = new ArrayList<>();

    public OperatorsResolver(HashMap<String, Class> operatorsMap, UObjectInterface rulesAndOperatorsArray) {
        this.operatorsMap = operatorsMap;
        this.rulesAndOperatorsArray = rulesAndOperatorsArray;
    }

    public ArrayList<OperatorInterface> resolve () throws OperatorsResolverException {

        if (rulesAndOperatorsArray.getAll().size() > 1) {

            try {

                ArrayList<String> operatorsArray = rulesAndOperatorsArray.get(rulesAndOperatorsArray.getAll().size() - 1);

                Iterator<String> operatorsArrayIterator = operatorsArray.iterator();
                while (operatorsArrayIterator.hasNext()) {

                    String operatorString = operatorsArrayIterator.next();
                    if (operatorsMap.containsKey(operatorString)) {

                        OperatorInterface operator = (OperatorInterface) operatorsMap.get(operatorString)
                                .getConstructor().newInstance();
                        operatorsList.add(operator);

                    } else
                        throw new OperatorsResolverException("Cannot resolve operator");

                }

            } catch (UObjectException e) {
                throw new OperatorsResolverException("Operators data are not valid");
            } catch (InvocationTargetException | InstantiationException | NoSuchMethodException | IllegalAccessException e) {
                throw new OperatorsResolverException("Cannot create object of operator class");
            }

            return operatorsList;

        } else if (rulesAndOperatorsArray.getAll().size() == 1) {
            return operatorsList;
        } else
            throw new OperatorsResolverException("No any data to resolve");

    }

}
