package org.halliburton.keycoak.user.utils;

import javax.ws.rs.client.ClientRequestContext;
import javax.ws.rs.client.ClientRequestFilter;
import javax.ws.rs.core.MultivaluedMap;
import java.io.IOException;

public class AuthFilter implements ClientRequestFilter {

    private String key;

    public AuthFilter(String key) {
        this.key = key;
    }

    @Override
    public void filter(ClientRequestContext requestContext) throws IOException {
        MultivaluedMap<String, Object> headers = requestContext.getHeaders();
        headers.add("key", key);
    }
}