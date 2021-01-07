package br.com.sascar.positionsconfigurationsbff.domains;

import org.springframework.http.HttpStatus;

import br.com.sascar.positionsconfigurationsbff.dtos.Response;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateResponsePage implements Response {
    private String createdAt;
    private String updatedAt;
    private String uid;
    private LinkResponsePage _links;
    private HttpStatus httpStatus;

    public HttpStatus getHttpStatus() {
        return this.httpStatus;
    }
}