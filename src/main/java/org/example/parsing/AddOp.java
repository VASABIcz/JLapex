package org.example.parsing;

public final class AddOp extends Operation {
    public AddOp(Operation left, Operation right) {
        this.children.add(left);
        this.children.add(right);
        this.name = "add";
    }
}
