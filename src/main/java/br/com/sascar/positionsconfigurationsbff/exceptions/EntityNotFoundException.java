package br.com.sascar.positionsconfigurationsbff.exceptions;

public class EntityNotFoundException extends Exception {
    private static final long serialVersionUID = 1L;

    public EntityNotFoundException(final String message) {
        super(message);
    }
}