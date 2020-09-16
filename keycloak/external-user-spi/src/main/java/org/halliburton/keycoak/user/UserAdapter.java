package org.halliburton.keycoak.user;

import org.halliburton.keycoak.user.utils.ExternalUser;
import org.keycloak.common.util.MultivaluedHashMap;
import org.keycloak.component.ComponentModel;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.RealmModel;
import org.keycloak.storage.StorageId;
import org.keycloak.storage.adapter.AbstractUserAdapterFederatedStorage;

import java.util.Collections;
import java.util.List;
import java.util.Map;

/**
 * @author André de Mattos Ferraz
 */
public class UserAdapter extends AbstractUserAdapterFederatedStorage {

    static final String REAL_USERNAME_ATTRIBUTE = "realUsername";

    private final ExternalUser user;
    private final String keycloakId;

    public UserAdapter(KeycloakSession session, RealmModel realm, ComponentModel model, ExternalUser user) {
        super(session, realm, model);
        this.user = user;
        this.keycloakId = StorageId.keycloakId(model, user.getId());
    }

    @Override
    public String getId() {
        return keycloakId;
    }

    @Override
    public String getUsername() {
        return user.getUsername();
    }

    @Override
    public void setUsername(String username) {
        user.setUsername(username);
    }

    @Override
    public String getEmail() {
        return user.getEmail();
    }

    @Override
    public void setEmail(String email) {
        user.setEmail(email);
    }

    @Override
    public String getFirstName() {
        return user.getFirstName();
    }

    @Override
    public void setFirstName(String firstName) {
        user.setFirstName(firstName);
    }

    @Override
    public String getLastName() {
        return user.getLastName();
    }

    @Override
    public void setLastName(String lastName) {
        user.setLastName(lastName);
    }

    @Override
    public void setSingleAttribute(String name, String value) {
        if (!REAL_USERNAME_ATTRIBUTE.equals(name)) {
            super.setSingleAttribute(name, value);
        }
    }

    @Override
    public void removeAttribute(String name) {
        if (!REAL_USERNAME_ATTRIBUTE.equals(name)) {
            super.removeAttribute(name);
        }
    }

    @Override
    public void setAttribute(String name, List<String> values) {
        if (!REAL_USERNAME_ATTRIBUTE.equals(name)) {
            super.setAttribute(name, values);
        }
    }

    @Override
    public String getFirstAttribute(String name) {
        if (REAL_USERNAME_ATTRIBUTE.equals(name)) {
            return user.getRealUsername();
        } else {
            return super.getFirstAttribute(name);
        }
    }

    @Override
    public List<String> getAttribute(String name) {
        if (REAL_USERNAME_ATTRIBUTE.equals(name)) {
            return Collections.singletonList(user.getRealUsername());
        } else {
            return super.getAttribute(name);
        }
    }

    @Override
    public Map<String, List<String>> getAttributes() {
        Map<String, List<String>> attrs = super.getAttributes();
        MultivaluedHashMap<String, String> all = new MultivaluedHashMap<>();
        all.putAll(attrs);
        all.add(REAL_USERNAME_ATTRIBUTE, user.getRealUsername());
        return all;
    }
}
