package com.bridgelab;

public class StateAnalyzerException extends Throwable {

    public enum ExceptionType {
        FILE_NOT_FOUND
    }

    public ExceptionType type;

    public StateAnalyzerException(ExceptionType type, String message) {
        super(message);
        this.type = type;
    }
}
