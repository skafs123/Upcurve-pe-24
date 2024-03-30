package com.targetindia.models;
import lombok.*;

@NoArgsConstructor
@Getter
@Setter
@ToString

public class LinesInFile {

    int lineNo= 0;
    int wordCount = 0 ;
    String sLine = null;

    public LinesInFile(int lineNo, String sLine) {
        this.lineNo = lineNo;
        this.sLine = sLine;
        //wordCount = parse the str to get the tokens
        String[] temp = sLine.split("\\s+");
        this.wordCount = temp.length;
    }

    public int compareTo(LinesInFile o)
    {
        return(o.wordCount - wordCount );
    }

}
