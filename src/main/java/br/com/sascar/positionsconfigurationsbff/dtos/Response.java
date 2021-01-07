package br.com.sascar.positionsconfigurationsbff.dtos;

import org.springframework.http.HttpStatus;

public interface Response {
    abstract HttpStatus getHttpStatus();
}
