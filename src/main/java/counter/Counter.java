package counter;

import common.uobject.UObjectInterface;
import rulesgroup.RulesGroupException;
import rulesgroup.RulesGroupInterface;

import java.util.ArrayList;
import java.util.Iterator;

public class Counter {

    private UObjectInterface units;
    private RulesGroupInterface rulesGroup;

    public Counter(UObjectInterface units, RulesGroupInterface rulesGroup) {
        this.units = units;
        this.rulesGroup = rulesGroup;
    }

    public int count() throws RulesGroupException {

        int count = 0;

        Iterator<ArrayList<String>> iterator = units.getAll().iterator();
        while (iterator.hasNext()) {
            if (rulesGroup.execute(iterator.next()))
                count++;
        }

        return count;

    }

}
