package org.example.parsing;

public enum ParsingUnitType {
    LookAround, // .*., .==.
    LookBack, ///   .[0]
    LookAhead //   5-test.name()*5
    // 5*test()-5
    //   ( 5 - test . name ( ) ) * 5
    //  5 - test . name ( )
    // 5[0][0]


    /*
    5*5
    5+-25
    //
    5 - 25
    5 - - 25
    (5+5)*69

    5+52*(6-8*5)
    parse()
    canThisTokenBeParsed?
        is lookBack
            buf = parseToken(buf)
        else
            buf = parseToken()
            repeat


     parseToken(pre)
     consumeToken()
     if pre
        parseToken
     */
}
