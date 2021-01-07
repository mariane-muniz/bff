package br.com.sascar.positionsconfigurationsbff.dtos;

import java.util.HashMap;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PayloadData {
    private Map<String, Object> requirementPayloadData = new HashMap<>();
}