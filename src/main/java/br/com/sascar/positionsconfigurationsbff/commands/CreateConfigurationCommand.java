package br.com.sascar.positionsconfigurationsbff.commands;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;

@Service
public class CreateConfigurationCommand extends AbstractCommand {
    
    @Override
    public String entityName() {
        return "simple_configurations";
    }

    @Override
    public void rollback() {
        // IGNORE
    }

    @Override
    public HttpMethod getHttpMethod() {
        return HttpMethod.POST;
    }

    @Override
    public List<AbstractCommand> getRequirements() {
        final List<AbstractCommand> requirements = new ArrayList<>();
        requirements.add((AbstractCommand)this.context.getBeansOfType(CreateSimpleFilterCommand.class));
        requirements.add((AbstractCommand)this.context.getBeansOfType(CreateSampleTargetCommand.class));
        return requirements;
    }
}