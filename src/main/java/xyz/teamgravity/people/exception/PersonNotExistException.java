package xyz.teamgravity.people.exception;

public class PersonNotExistException extends RuntimeException {
    public PersonNotExistException(String message) {
        super(message);
    }
}
