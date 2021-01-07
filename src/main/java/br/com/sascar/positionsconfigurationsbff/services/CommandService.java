package br.com.sascar.positionsconfigurationsbff.services;

import java.util.List;
import java.util.Optional;

import javax.annotation.Resource;

import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import br.com.sascar.positionsconfigurationsbff.commands.AbstractCommand;
import br.com.sascar.positionsconfigurationsbff.dtos.ResponseData;
import br.com.sascar.positionsconfigurationsbff.exceptions.EntityNotFoundException;

@Service
public class CommandService {
    @Resource
    private List<AbstractCommand> commandList;

    public ResponseData perform(final String entity, final Object payloadData)
            throws EntityNotFoundException, HttpClientErrorException, JSONException {
        final Optional<AbstractCommand> command = this.getCommand(entity);
        if (command.isPresent()) {
            return command.get().setPayload(payloadData).execute();
        }
        else throw new EntityNotFoundException("Entity not found: " + entity);
    }

    private Optional<AbstractCommand> getCommand(final String entity) {
        return this.commandList.stream()
                .filter(predicate -> predicate.entityName().equals(entity)).findFirst();
    }
}