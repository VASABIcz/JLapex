package org.example.lexing;

import lombok.Data;
import lombok.ToString;

@Data
public class SourceCode {
    @ToString.Exclude
    String text;
    String filePath;

    public SourceCode(String filePath) {
        throw new RuntimeException("sc from path not implemented");
    }

    public SourceCode(String filePath, String source) {
        this.filePath = filePath;
        this.text = source;
    }
}
