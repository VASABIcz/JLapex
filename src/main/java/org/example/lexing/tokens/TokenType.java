package org.example.lexing.tokens;

public enum TokenType {
    Identifier,

    OperatorEq,
    OperatorNotEq,
    OperatorNot,

    // brackets
    ORB, // (
    CRB,
    OSB, // [
    CSB,
    OCB, // {
    CCB,
    OTB, // <
    CTB,

    Plus,
    Minus,
    Star,
    Dot, // .
    Comma, // ,
    Slash,
    Semicolon,
    Colon, // :

    Eq,

    String,
    FloatingPoint,
    Numeric,

    // keyword
    Loop,
    Break,
    Continue,
    Return,
    FunctionDef,
    VariableDef,

    Ind,
    Dec,

    DivAssign,
    AddAssign,
    SubAssign,
    MultAssign,

    Char
}