package org.example.lexing;

import lombok.extern.log4j.Log4j2;

@Log4j2
public class SourceProvider {
    SourceCode sourceCode;
    Integer sourceIndex = -1;


    public SourceProvider(SourceCode sourceCode) {
        this.sourceCode = sourceCode;
    }

    Boolean isDone() {
        return sourceIndex >= sourceCode.text.length() - 1;
    }

    public void consumeSource() {
        log.info("consumed: " + peekSource());
        log.debug("index: " + sourceIndex + ", textsize: " + sourceCode.text.length());
        sourceIndex++;
    }

    public void consumeSource(Integer amount) {
        log.info("consumed: " + peekSource(amount));
        log.debug("index: " + sourceIndex + ", textsize: " + sourceCode.text.length());
        sourceIndex += amount;
    }

    public Character peekSource() {
        log.debug("index: " + sourceIndex + ", textsize: " + sourceCode.text.length());
        try {
            return sourceCode.text.charAt(sourceIndex + 1);
        } catch (StringIndexOutOfBoundsException e) {
            return EOF();
        }
    }

    public String peekSource(Integer amount) {
        try {
            if (amount < 0) {
                return sourceCode.text.substring(sourceIndex - 1, sourceIndex + amount);
            } else if (amount > 0) {
                return sourceCode.text.substring(sourceIndex + 1, sourceIndex + amount + 1);
            } else {
                log.debug("requested amount = 0");
                return "";
            }
        }
        catch (StringIndexOutOfBoundsException e) {
            return "";
        }
    }

    public SourceCodePosition getCurrentPosition(Integer len) {
        return new SourceCodePosition(sourceCode, sourceIndex, sourceIndex+len);
    }

    public SourceCodePosition getCurrentPosition() {
        return new SourceCodePosition(sourceCode, sourceIndex, sourceIndex);
    }

    public static Character EOF() {
        return '\u001a';
    }
}
