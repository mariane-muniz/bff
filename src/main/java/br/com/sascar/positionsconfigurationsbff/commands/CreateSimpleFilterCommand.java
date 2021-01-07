package br.com.sascar.positionsconfigurationsbff.commands;

import java.util.Collections;
import java.util.List;

import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

@Service
public class CreateSimpleFilterCommand extends AbstractCommand {

    @Override
    public String entityName() {
        return "simple_filters";
    }

    @Override
    public void rollback() {
    }

    @Override
    public HttpMethod getHttpMethod() {
        return HttpMethod.POST;
    }

    @Override
    public List<AbstractCommand> getRequirements() {
        return Collections.emptyList();
    }
}