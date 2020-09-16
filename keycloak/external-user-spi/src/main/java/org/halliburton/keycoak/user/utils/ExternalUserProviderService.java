package org.halliburton.keycoak.user.utils;

import org.jboss.logging.Logger;
import org.jboss.resteasy.client.jaxrs.ResteasyClient;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.jboss.resteasy.client.jaxrs.ResteasyWebTarget;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.ws.rs.ClientErrorException;
import javax.ws.rs.core.Response;

/**
 * @author André de Mattos Ferraz
 */
public class ExternalUserProviderService {

    private static final Logger logger = Logger.getLogger(ExternalUserProviderService.class);

    private ExternalUserProviderClient externalUserClient;

    public ExternalUserProviderService(String url, String key) {
        buildClient(url, key);
    }

    private void buildClient(String url, String key) {
        ResteasyClient client = new ResteasyClientBuilder()
                .register(new AuthFilter(key))
                .register(new RestLoggingFilter())
                .connectionPoolSize(10)
                .maxPooledPerRoute(5)
                .disableTrustManager()
                .build();
        ResteasyWebTarget target = client.target(url);
        this.externalUserClient = target.proxyBuilder(ExternalUserProviderClient.class).classloader(this.getClass().getClassLoader()).build();
    }

    public ExternalUser getUser(String username) {
        ExternalUser user = null;
        try {
            String userString = externalUserClient.getUser(username);
            user = this.parseUserString(username, userString);
        } catch (Exception e) {
            handleException(e, "getUser");
        }
        logger.info("User: " + (user != null ? user.toString() : null));
        return user;
    }

    public ExternalUser getUserById(String userId) {
        ExternalUser user = null;
        try {
            String userString = externalUserClient.getUserById(userId);
            user = parseUserString(userId, userString);
        } catch (Exception e) {
            handleException(e, "getUserById");
        }
        logger.info("User: " + (user != null ? user.toString() : null));
        return user;
    }

    private ExternalUser parseUserString(String userId, String userString) {
        logger.info("Got userdata response for userId " + userId + ": " + userString.trim());
        ExternalUser user = null;
        try {
        	ObjectMapper mapper = new ObjectMapper();
        	user = mapper.readValue(userString, ExternalUser.class);
        }catch(Exception e) {
        	logger.info("=========> fail to parse json:" + e.getMessage());
        	return null;
        }
        return user;
    }

    public boolean validateUserPassword(String username, String password) {
        try {
            String validationResult = externalUserClient.validatePassword(username, password);
            return Boolean.parseBoolean(validationResult.trim());
        } catch (Exception e) {
            handleException(e, "validatePassword");
            return false;
        }
    }

    private void handleException(Exception ex, String serviceName) {
        if (ex instanceof ClientErrorException) {
            ClientErrorException cee = (ClientErrorException) ex;
            Response response = cee.getResponse();
            Response.StatusType statusInfo = response.getStatusInfo();
            logger.error("Error while calling REST-Serivce " + serviceName + ", got response: " + statusInfo.getStatusCode() + " " + statusInfo.getReasonPhrase());
        } else {
            logger.error("Exception while calling REST-Service " + serviceName, ex);
        }
    }
}
