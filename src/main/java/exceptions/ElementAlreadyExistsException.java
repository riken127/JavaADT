package exceptions;

public class ElementAlreadyExistsException extends Exception{
    public ElementAlreadyExistsException() {
    }

    public ElementAlreadyExistsException(String message) {
        super(message);
    }
}
