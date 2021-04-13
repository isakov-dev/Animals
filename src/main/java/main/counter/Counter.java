package main.counter;

import common.uobject.UObjectInterface;
import rulesgroup.RulesGroupInterface;

public class Counter {

    private UObjectInterface units;
    private RulesGroupInterface rules;

    public Counter(UObjectInterface units, RulesGroupInterface rules) {
        this.units = units;
        this.rules = rules;
    }

    public int count() {



        return 0;

    }

}
