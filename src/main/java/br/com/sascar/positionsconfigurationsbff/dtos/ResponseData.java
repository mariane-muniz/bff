package br.com.sascar.positionsconfigurationsbff.dtos;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseData {
    private HttpStatus httpStatus;
    private String body;
    private boolean valid;
}
