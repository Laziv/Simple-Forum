package ua.lviv.lgs.nl.simpleforum.exception;

public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(Long id) {
        super("Resource with id " + id + " was not found");
    }
    public ResourceNotFoundException(String message) {
        super(message);
    }
}
