package com.bridgelab.sensusanalyser.exception;

public class StateAnalyzerException extends Throwable {

    public enum ExceptionType {
        FILE_NOT_FOUND, NO_SUCH_TYPE, WRONG_DELIMITER_OR_HEADER, NO_SUCH_CENSUS_DATA, NO_SUCH_COUNTRY;
    }

    public ExceptionType type;

    public StateAnalyzerException(ExceptionType type, String message) {
        super(message);
        this.type = type;
    }
}
