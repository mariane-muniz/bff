package br.com.sascar.positionsconfigurationsbff.dtos;

import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponse implements Response {
    private HttpStatus status;
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm:ss")
    private Date timestamp;
    private Object message;
    private String errorCode;

    @Override
    public HttpStatus getHttpStatus() {
        return this.getHttpStatus();
    }
}