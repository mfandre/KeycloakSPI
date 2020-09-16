package org.halliburton.keycoak.user;

import org.halliburton.keycoak.user.utils.ExternalUserProviderService;
import org.keycloak.component.ComponentModel;
import org.keycloak.models.KeycloakSession;
import org.keycloak.provider.ProviderConfigProperty;
import org.keycloak.provider.ProviderConfigurationBuilder;
import org.keycloak.storage.UserStorageProviderFactory;

import java.util.List;

/**
 * @author André de Mattos Ferraz
 */
public class ExternalUserStorageProviderFactory implements UserStorageProviderFactory<ExternalUserStorageProvider> {

    @Override
    public ExternalUserStorageProvider create(KeycloakSession session, ComponentModel model) {
    	ExternalUserProviderService service = new ExternalUserProviderService(model.get("url"), model.get("key"));
        return new ExternalUserStorageProvider(session, model, service);
    }

    @Override
    public String getId() {
        return "external-user-provider";
    }

    @Override
    public List<ProviderConfigProperty> getConfigProperties() {
        return ProviderConfigurationBuilder.create()
                .property("url", "URL", "url of your rest api", ProviderConfigProperty.STRING_TYPE, "", null)
                .property("key", "Key", "", ProviderConfigProperty.STRING_TYPE, "", null)
                .build();
    }
}
