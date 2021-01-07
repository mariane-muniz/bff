package br.com.sascar.positionsconfigurationsbff.exceptions;

import java.io.IOException;

import org.springframework.http.HttpStatus;

import lombok.Getter;

@Getter
public class RequestFailException extends IOException {
    private static final long serialVersionUID = 1L;
    private HttpStatus status;

    public RequestFailException(final String message, final HttpStatus status) {
        super(message);
        this.status = status;
    }
}