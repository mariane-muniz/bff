package br.com.sascar.positionsconfigurationsbff.commands;

import java.util.Collections;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.context.ApplicationContext;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

import br.com.sascar.positionsconfigurationsbff.dtos.CommandExecutionData;
import br.com.sascar.positionsconfigurationsbff.dtos.RequestData;
import br.com.sascar.positionsconfigurationsbff.dtos.ResponseData;
import br.com.sascar.positionsconfigurationsbff.enums.CommandExecutionStatusEnum;
import br.com.sascar.positionsconfigurationsbff.services.RestService;

abstract public class AbstractCommand {
    @Resource
    protected RestService restService;
    @Autowired
    protected ApplicationContext context;
    private List<AbstractCommand> requirements;
    private Object payload;
    private CommandExecutionData commandExecution;
    private HttpStatus httpStatus;

    public abstract String entityName();

    public abstract void rollback();

    public abstract HttpMethod getHttpMethod();

    public abstract List<AbstractCommand> getRequirements();

    public AbstractCommand setPayload(final Object payload) {
        this.commandExecution = new CommandExecutionData();
        this.requirements = Collections.emptyList();
        this.payload = payload;
        return this;
    }

    public ResponseData execute() throws HttpClientErrorException, JSONException {
        this.validate();
        this.executeRequirements();
        this.checkState();
        this.executeRequest();

        final StringBuilder message = new StringBuilder();
        this.commandExecution.getMessage().forEach(m -> message.append(m));
        final ResponseData response = new ResponseData();
        response.setHttpStatus(this.httpStatus);
        response.setBody(message.toString());
        response.setValid(this.commandExecution.getStatus() == CommandExecutionStatusEnum.SUCCESS);
        return response;
    }

    private void executeRequirements() {
        this.commandExecution.setStatus(CommandExecutionStatusEnum.SUCCESS);
        this.requirements.forEach(command -> {
            if (this.commandExecution.getStatus().equals(CommandExecutionStatusEnum.SUCCESS)) {
                try {
                    command.setPayload(null).execute();
                } catch (HttpClientErrorException | JSONException e) {
                    e.printStackTrace();
                }
                final CommandExecutionStatusEnum status = command.getCommandExecution().getStatus();
                if (status.equals(CommandExecutionStatusEnum.FAIL)) {
                    this.commandExecution.setStatus(CommandExecutionStatusEnum.FAIL);
                }
            }
        });
    }

    private void validate() {
        this.requirements.forEach(command -> {
            final CommandExecutionStatusEnum status = command.getCommandExecution().getStatus();
            if (status.equals(CommandExecutionStatusEnum.SUCCESS)) {
                command.rollback();
            }
        });
    }

    private void executeRequest() throws HttpClientErrorException, JSONException {
        if (this.commandExecution.getStatus().equals(CommandExecutionStatusEnum.SUCCESS)) {
            final String key = this.entityName();
            final Object commandPayload = this.payload;
            
            final RequestData payloadData = new RequestData();
            payloadData.setHttpMethod(this.getHttpMethod());
            payloadData.setPayload(commandPayload);
            
            final ResponseData response = this.restService.sendRequest(payloadData);
            this.commandExecution.getMessage().add(response.getBody());
            this.commandExecution.setStatus(
                response.isValid()
                    ? CommandExecutionStatusEnum.SUCCESS
                    : CommandExecutionStatusEnum.FAIL
            );
            this.httpStatus = response.getHttpStatus();
        }
    }

    private void checkState() {
        this.requirements.forEach(command -> {
            final CommandExecutionData execution = command.getCommandExecution();
            if (CommandExecutionStatusEnum.FAIL.equals(execution.getStatus())){
                this.commandExecution.getMessage().addAll(execution.getMessage());
            }
        });
    }

    public CommandExecutionData getCommandExecution() {
        return this.commandExecution;
    }
}