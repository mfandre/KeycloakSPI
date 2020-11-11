import { KeycloakService } from 'keycloak-angular';
import { environment } from './environments/environment';
 
export function initializer(keycloak: KeycloakService): () => Promise<any> {
  return (): Promise<any> => {
    console.log("aaaaaaaaaaaa");
    
    return keycloak.init({
      config: environment.keycloak,
      initOptions: {
        onLoad: 'login-required',
        checkLoginIframe: false,
      },
      enableBearerInterceptor: true,
      //bearerExcludedUrls: ['/assets', '/clients/public']
    });
  }
}


/*export function initializer(keycloak: KeycloakService): () => Promise<any> {
  return (): Promise<any> => {
    return new Promise(async (resolve, reject) => {
      try {
        await keycloak.init({
          config: environment.keycloak,
          initOptions: {
            onLoad: 'check-sso',
            checkLoginIframe: false
          },
          enableBearerInterceptor: true,
          bearerPrefix: 'Bearer',
          bearerExcludedUrls: [
            'main.js',
          ]
        });
        resolve();
      } catch (error) {
        console.log("bbbbbbbbbbb", error)
        reject(error);
      }
    });
  };
 }*/