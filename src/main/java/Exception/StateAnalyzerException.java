package Exception;

public class StateAnalyzerException extends Throwable {

    public enum ExceptionType {
        FILE_NOT_FOUND,NO_SUCH_TYPE,WRONG_DELIMITER_OR_HEADER,NO_SUCH_CENSUS_DATA;
    }

    public ExceptionType type;

    public StateAnalyzerException(ExceptionType type, String message) {
        super(message);
        this.type = type;
    }
}
