package org.halliburton.keycoak.user;

import org.halliburton.keycoak.user.utils.ExternalUserProviderService;
import org.halliburton.keycoak.user.utils.ExternalUser;
import org.jboss.logging.Logger;
import org.keycloak.component.ComponentModel;
import org.keycloak.credential.CredentialInput;
import org.keycloak.credential.CredentialInputValidator;
import org.keycloak.credential.CredentialModel;
import org.keycloak.models.ClientModel;
import org.keycloak.models.GroupModel;
import org.keycloak.models.KeycloakSession;
import org.keycloak.models.RealmModel;
import org.keycloak.models.RoleModel;
import org.keycloak.models.UserCredentialModel;
import org.keycloak.models.UserModel;
import org.keycloak.storage.StorageId;
import org.keycloak.storage.UserStorageProvider;
import org.keycloak.storage.user.UserLookupProvider;
import org.keycloak.storage.user.UserQueryProvider;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author André de Mattos Ferraz
 */
public class ExternalUserStorageProvider
		implements UserStorageProvider, UserLookupProvider, UserQueryProvider, CredentialInputValidator {
	private static final Logger logger = Logger.getLogger(ExternalUserStorageProvider.class);
	private final KeycloakSession session;
	private final ComponentModel model;
	private final ExternalUserProviderService externalUserService;

	public ExternalUserStorageProvider(KeycloakSession session, ComponentModel model,
			ExternalUserProviderService externalUserService) {
		this.session = session;
		this.model = model;
		this.externalUserService = externalUserService;
	}

	private void addToStorage(RealmModel realm, String username) {
		UserModel local = session.userLocalStorage().getUserByUsername(username, realm);
		logger.info("local ======>" + local);
        if (local == null) {
            local = session.userLocalStorage().addUser(realm, username);
            local.setFederationLink(model.getId());
            local.setEnabled(true);
            local.grantRole(realm.getRole("admin"));// here you need to added what do you want...
            local.grantRole(realm.getRole("create-realm"));// here you need to added what do you want...
            logger.info("added to local <======");
            session.userCache().clear();
        }
	}
	
	private UserModel getFromStorage(RealmModel realm, String username) {
		UserModel local = session.userLocalStorage().getUserByUsername(username, realm);
		logger.info("local ======>" + local);
		return local;
	}
	
	private Set<RoleModel> getDefaultRoles(RealmModel realm) {
		Set<RoleModel> set = new HashSet<>();
        for (String r : realm.getDefaultRoles()) {
            set.add(realm.getRole(r));
            logger.info("defRole ======>" + r);
        }
 
        for (ClientModel application : realm.getClients()) {
            for (String r : application.getDefaultRoles()) {
                set.add(application.getRole(r));
                logger.info("clientRole ======>" + r);
            }
        }
        
        return set;
	}
	
	@Override
	public UserModel getUserById(String id, RealmModel realm) {
		String externalId = StorageId.externalId(id);
		ExternalUser externalUser = externalUserService.getUserById(externalId);
		if (null != externalUser) {
			UserCache.addUser(externalUser);
			return new UserAdapter(session, realm, model, externalUser);
		} else {
			return null;
		}
	}

	@Override
	public UserModel getUserByUsername(String username, RealmModel realm) {
		ExternalUser externalUser = externalUserService.getUser(username);
		if (null != externalUser) {
			UserCache.addUser(externalUser);
			return new UserAdapter(session, realm, model, externalUser);
		} else {
			return null;
		}
	}

	@Override
	public UserModel getUserByEmail(String email, RealmModel realm) {
		return getUserByUsername(email, realm);
	}

	@Override
	public boolean supportsCredentialType(String credentialType) {
		return CredentialModel.PASSWORD.equals(credentialType);
	}

	@Override
	public boolean isConfiguredFor(RealmModel realm, UserModel user, String credentialType) {
		return supportsCredentialType(credentialType);
	}

	@Override
	public boolean isValid(RealmModel realm, UserModel user, CredentialInput input) {
		logger.info("isValid");
		if (!supportsCredentialType(input.getType()) || !(input instanceof UserCredentialModel)) {
			return false;
		}
		UserCredentialModel cred = (UserCredentialModel) input;
		//String realUsername = user.getFirstAttribute(UserAdapter.REAL_USERNAME_ATTRIBUTE);
		boolean rtn = externalUserService.validateUserPassword(user.getUsername(), cred.getValue());
		
		if(rtn) {
			logger.info("isValid = " + user.getUsername());
			addToStorage(realm, user.getUsername());
		}
		
		return rtn;
	}

	@Override
	public void preRemove(RealmModel realm) {
	}

	@Override
	public void preRemove(RealmModel realm, GroupModel group) {
	}

	@Override
	public void preRemove(RealmModel realm, RoleModel role) {
	}

	@Override
	public void close() {
	}

	@Override
	public int getUsersCount(RealmModel realm) {
		return UserCache.getCount();
	}

	@Override
	public List<UserModel> getUsers(RealmModel realm) {
		return UserCache.getUsers().stream().map(user -> new UserAdapter(session, realm, model, user))
				.collect(Collectors.toList());
	}

	@Override
	public List<UserModel> getUsers(RealmModel realm, int firstResult, int maxResults) {
		return getUsers(realm);
	}

	@Override
	public List<UserModel> searchForUser(String search, RealmModel realm) {
		return UserCache.findUsers(search).stream().map(user -> new UserAdapter(session, realm, model, user))
				.collect(Collectors.toList());
	}

	@Override
	public List<UserModel> searchForUser(String search, RealmModel realm, int firstResult, int maxResults) {
		return searchForUser(search, realm);
	}

	@Override
	public List<UserModel> searchForUser(Map<String, String> params, RealmModel realm) {
		logger.info("searchForUser2");
		return Collections.emptyList();// changed
	}

	@Override
	public List<UserModel> searchForUser(Map<String, String> params, RealmModel realm, int firstResult,
			int maxResults) {
		logger.info("searchForUser1");
		return Collections.emptyList();// changed
	}

	@Override
	public List<UserModel> getGroupMembers(RealmModel realm, GroupModel group, int firstResult, int maxResults) {
		return Collections.emptyList();
	}

	@Override
	public List<UserModel> getGroupMembers(RealmModel realm, GroupModel group) {
		return Collections.emptyList();
	}

	@Override
	public List<UserModel> searchForUserByUserAttribute(String attrName, String attrValue, RealmModel realm) {
		return Collections.emptyList();
	}
}
