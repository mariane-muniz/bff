package br.com.sascar.positionsconfigurationsbff.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ConfigurationData extends PayloadData {
    private SimpleTargetData target;
}
