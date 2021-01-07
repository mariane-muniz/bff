// package br.com.sascar.positionsconfigurationsbff.handlers;

// import java.io.IOException;

// import org.springframework.http.HttpStatus;
// import org.springframework.http.client.ClientHttpResponse;
// import org.springframework.stereotype.Component;
// import org.springframework.web.client.ResponseErrorHandler;

// import br.com.sascar.positionsconfigurationsbff.exceptions.RequestFailException;
// import lombok.extern.slf4j.Slf4j;

// @Slf4j
// @Component
// public class RestTemplateResponseErrorHandler implements ResponseErrorHandler {

//     @Override
//     public boolean hasError(final ClientHttpResponse response) throws IOException {
//         log.error(response.getStatusCode().toString());
//         log.error(String.valueOf(response.getStatusCode().series().equals(HttpStatus.Series.SERVER_ERROR)));
//         return response.getStatusCode().series() == HttpStatus.Series.SERVER_ERROR
//                 || response.getStatusCode().series() == HttpStatus.Series.CLIENT_ERROR;
//     }

//     @Override
//     public void handleError(final ClientHttpResponse response) throws IOException {
//         final String message = response.getBody().toString();
//         final HttpStatus status = response.getStatusCode();
//         throw new RequestFailException(message, status);
//     }
// }