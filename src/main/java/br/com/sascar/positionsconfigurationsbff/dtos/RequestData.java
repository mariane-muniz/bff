package br.com.sascar.positionsconfigurationsbff.dtos;

import org.springframework.http.HttpMethod;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestData {
    private HttpMethod httpMethod;
    private Object payload;
}