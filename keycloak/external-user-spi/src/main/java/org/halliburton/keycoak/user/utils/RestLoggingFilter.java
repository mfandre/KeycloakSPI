package org.halliburton.keycoak.user.utils;

import org.jboss.logging.Logger;

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.client.ClientResponseContext;
import javax.ws.rs.client.ClientResponseFilter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

/**
 * @author André de Mattos Ferraz
 */
@Provider
public class RestLoggingFilter implements ClientRequestFilter, ClientResponseFilter {

    private static final Logger logger = Logger.getLogger(RestLoggingFilter.class);

    @Override
    public void filter(ClientRequestContext clientRequestContext) throws IOException {
        logger.info("===> REQUEST: " + clientRequestContext.getUri().toString());
        String username = (String) clientRequestContext.getHeaders().getFirst("user");
        logger.info("Username: " + username);
    }

    @Override
    public void filter(ClientRequestContext clientRequestContext, ClientResponseContext clientResponseContext) throws IOException {
        logger.info("===> RESPONSE: " + clientResponseContext.getStatusInfo().getReasonPhrase());
    }
}
