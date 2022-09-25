package org.example.parsing;

public final class MultiplyOp extends Operation {
    public MultiplyOp(Operation left, Operation right) {
        this.children.add(left);
        this.children.add(right);
        this.name = "mul";
    }
}
