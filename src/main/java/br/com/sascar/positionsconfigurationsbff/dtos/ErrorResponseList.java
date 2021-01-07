package br.com.sascar.positionsconfigurationsbff.dtos;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ErrorResponseList {
    private List<ErrorResponse> errors = new ArrayList<>();
}