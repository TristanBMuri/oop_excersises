package Week11.Temperature;

public class EmptyHistoryException extends Exception {
    public EmptyHistoryException(String errorMessage){
        super(errorMessage);
    }
}
