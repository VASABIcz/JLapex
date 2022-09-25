package org.example.lexing;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class SourceCodePosition {

    public SourceCodePosition(SourceCode sourceCode, Integer start) {
        this.sourceCode = sourceCode;
        this.start = start;
    }

    public SourceCodePosition(SourceCode sourceCode, Integer start, Integer end) {
        this.sourceCode = sourceCode;
        this.start = start;
        this.end = end;
    }

    SourceCode sourceCode;
    Integer start;
    Integer end = 1;

    Integer getStartPosition() {
        return null;
    }

    Integer getLenaght() {
        return end - start;
    }

    public void setEnd(Integer end) {
        this.end = end;
    }

    public void setLength(Integer length) {
        end = start+length;
    }
}
