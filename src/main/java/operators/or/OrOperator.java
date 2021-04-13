package operators.or;

import operators.operatorinterface.OperatorInterface;

public class OrOperator implements OperatorInterface {

    public boolean apply(boolean bool1, boolean bool2) {
        return (bool1 || bool2);
    }

}
