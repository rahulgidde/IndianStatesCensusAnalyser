package com.bridgelab.exception;

public class CsvBuilderException extends Throwable {

    public StateAnalyzerException.ExceptionType type;

    //ENUM CLASS
    public enum ExceptionType {
        UNABLE_TO_PARSE;
    }

    //CONSTRUCTOR
    public CsvBuilderException(StateAnalyzerException.ExceptionType type, String message) {
        super(message);
        this.type = type;
    }
}