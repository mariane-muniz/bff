package br.com.sascar.positionsconfigurationsbff.populators;

import java.io.IOException;

import br.com.sascar.positionsconfigurationsbff.dtos.ResponseData;

public interface ResponsePopulator {
    public ResponseData populate(Object object) throws IOException;
}