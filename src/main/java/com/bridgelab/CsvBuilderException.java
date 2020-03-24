package com.bridgelab;

public class CsvBuilderException extends Exception {
    enum MyException_Type {
        UNABLE_TO_PARSE;
    }

    MyException_Type type;

    //CONSTRUCTOR
    public CsvBuilderException(MyException_Type type, String message) {
        super(message);
        this.type = type;
    }
}

