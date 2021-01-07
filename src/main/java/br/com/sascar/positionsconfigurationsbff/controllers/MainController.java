package br.com.sascar.positionsconfigurationsbff.controllers;

import org.springframework.boot.configurationprocessor.json.JSONException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;

import br.com.sascar.positionsconfigurationsbff.dtos.ResponseData;
import br.com.sascar.positionsconfigurationsbff.exceptions.EntityNotFoundException;
import br.com.sascar.positionsconfigurationsbff.services.CommandService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequestMapping
@RequiredArgsConstructor
public class MainController {
    private final CommandService commandService;

    @PostMapping("{entity}")
    public ResponseEntity<String> create(@PathVariable String entity, @RequestBody Object payloadData) throws HttpClientErrorException, JSONException {
        try {
            final ResponseData response = this.commandService.perform(entity, payloadData);
            return ResponseEntity.status(response.getHttpStatus()).body(response.getBody());
        }
        catch(EntityNotFoundException e) {
            log.error(e.getMessage());
        }
        return ResponseEntity.badRequest().build();
    }
}