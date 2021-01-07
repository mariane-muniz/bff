package br.com.sascar.positionsconfigurationsbff.populators;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import br.com.sascar.positionsconfigurationsbff.domains.CreateResponsePage;
import br.com.sascar.positionsconfigurationsbff.dtos.ResponseData;

@Service
public class DefaultResponseDataPopulator  {

    public ResponseData populate(final ResponseEntity<CreateResponsePage> response) {
        Assert.notNull(response, "response cannot be null");
        final ResponseData responseData = new ResponseData();
        responseData.setHttpStatus(response.getStatusCode());
        responseData.setValid(true);
        responseData.setBody(response.getBody().get_links().getSimpleTarget().getHref());
        return responseData;
    }
}