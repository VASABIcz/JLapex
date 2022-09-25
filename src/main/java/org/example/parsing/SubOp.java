package org.example.parsing;

public final class SubOp extends Operation {
    public SubOp(Operation left, Operation right) {
        this.children.add(left);
        this.children.add(right);
        this.name = "sub";
    }
}
