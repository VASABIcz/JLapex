package org.example.parsing;

public abstract class ParsingUnit {
    public ParsingUnitType type = ParsingUnitType.LookAhead;
    public Integer priority = null;

    abstract public Boolean canParse(Parser parser);

    abstract public Operation parse(Parser parser, Operation previous);

    abstract public Integer getNext(Parser parser, Integer offset);
    public Boolean isPrioritised(ParsingUnit unit) {
        return this.priority <= unit.priority;
    }
}
