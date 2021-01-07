package br.com.sascar.positionsconfigurationsbff.services;

import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;

import br.com.sascar.positionsconfigurationsbff.clients.RestClient;
import br.com.sascar.positionsconfigurationsbff.domains.CreateResponsePage;
import br.com.sascar.positionsconfigurationsbff.dtos.RequestData;
import br.com.sascar.positionsconfigurationsbff.dtos.ResponseData;
import br.com.sascar.positionsconfigurationsbff.exceptions.RequestFailException;
import br.com.sascar.positionsconfigurationsbff.populators.DefaultResponseDataPopulator;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class RestService {
    private final RestClient restClient;
    private final DefaultResponseDataPopulator defaultResponsePopulator;

    public ResponseData sendRequest(final RequestData requestData) throws HttpClientErrorException, JSONException {
        Assert.notNull(requestData, "requestData cannot be null");
        ResponseData responseData = null;
        try {
            final ResponseEntity<CreateResponsePage> response = this.restClient.sendRequest(requestData);
            responseData = this.defaultResponsePopulator.populate(response);
        } 
        catch (ResourceAccessException e) {
            log.error(e.getMessage());
            if (e.getCause() instanceof RequestFailException) {
                responseData = new ResponseData();
                responseData.setHttpStatus(((RequestFailException) e.getCause()).getStatus());
                responseData.setValid(false);
                responseData.setBody(e.getMessage());
            }
        }
        return responseData;
    }
}