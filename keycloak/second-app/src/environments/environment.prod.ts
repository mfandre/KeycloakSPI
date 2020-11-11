import { KeycloakConfig } from 'keycloak-angular';

// Add here your keycloak setup infos
let keycloakConfig: KeycloakConfig = {
  url: 'http://localhost:8989/auth',
  realm: 'master',
  clientId: 'custom-app',
  /*credentials: {
    secret: "cb1e4cee-c7c5-4d9d-b12f-eaa4f8da9dc7"
  },*/
};

export const environment = {
  production: true,
  keycloak: keycloakConfig
};
