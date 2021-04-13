package rulesgroup;

import java.util.ArrayList;

public interface RulesGroupInterface {

    boolean execute(ArrayList<String> properties) throws RulesGroupException;

}
