package br.com.sascar.positionsconfigurationsbff.dtos;

import java.util.ArrayList;
import java.util.List;

import br.com.sascar.positionsconfigurationsbff.enums.CommandExecutionStatusEnum;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommandExecutionData {
    private CommandExecutionStatusEnum status;
    private List<String> message = new ArrayList<>();
}
