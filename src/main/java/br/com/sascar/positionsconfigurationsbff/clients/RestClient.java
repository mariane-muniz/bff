package br.com.sascar.positionsconfigurationsbff.clients;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.boot.configurationprocessor.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;

import br.com.sascar.positionsconfigurationsbff.domains.CreateResponsePage;
import br.com.sascar.positionsconfigurationsbff.dtos.ErrorResponse;
import br.com.sascar.positionsconfigurationsbff.dtos.ErrorResponseList;
import br.com.sascar.positionsconfigurationsbff.dtos.RequestData;
import br.com.sascar.positionsconfigurationsbff.dtos.Response;
import lombok.RequiredArgsConstructor;
import net.minidev.json.parser.JSONParser;
import net.minidev.json.parser.ParseException;

@Service
@RequiredArgsConstructor
public class RestClient {
    private static final String SIMPLE_TARGET_PATH = "simple_targets";
    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Value("${cluster.base.url}")
    private String clusterBaseUrl;

    public ResponseEntity<Response> sendRequest(final RequestData requestData)
            throws HttpClientErrorException, JSONException {
        Assert.notNull(requestData, "requestData cannot be null.");

        final String URL = this.clusterBaseUrl + SIMPLE_TARGET_PATH;

        final HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);

        JSONObject personJsonObject = new JSONObject();
        personJsonObject.put("uid", "asdsad4");
        personJsonObject.put("topicName", "topic-name-test4");

        final HttpEntity<Object> request = new HttpEntity<>(personJsonObject.toString(), headers);

        try {
            ResponseEntity<CreateResponsePage> response = restTemplate.exchange(URL, requestData.getHttpMethod(),
                    request, CreateResponsePage.class);

            return (ResponseEntity<CreateResponsePage>) response;
            // String personResultAsJsonStr =
            // restTemplate.postForObject(URL, request, String.class);
            // JsonNode root = objectMapper.readTree(personResultAsJsonStr);
            // root.asInt();
        } catch (HttpClientErrorException e) {
            final ObjectMapper mapper = new ObjectMapper();
            try {
                ErrorResponse errorResponse = mapper.readValue(e.getResponseBodyAsString(), ErrorResponse.class);
                errorResponse.hashCode();
            } catch ( JsonProcessingException e1) {
                // TODO Auto-generated catch block
                e1.printStackTrace();
            }
        }

        // TODO ResourceAccessException implementar tratamento para quando indisponível
        catch(ResourceAccessException e){

        }
        return null;
    }
}