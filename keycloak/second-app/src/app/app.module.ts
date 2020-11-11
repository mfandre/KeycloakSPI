import { BrowserModule } from '@angular/platform-browser';
import { NgModule, APP_INITIALIZER, DoBootstrap, ApplicationRef } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { SomeComponentComponent } from './some-component/some-component.component';
import { OtherComponentComponent } from './other-component/other-component.component';
import { KeycloakAngularModule, KeycloakService } from 'keycloak-angular';
import { initializer } from 'src/app-init';
import { environment } from 'src/environments/environment';
import { resolve } from 'q';

const keycloakService = new KeycloakService();

/*@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    KeycloakAngularModule,
    BrowserModule,
    AppRoutingModule
  ],
  providers: [
    {
      provide: APP_INITIALIZER,
      useFactory: initializer,
      multi: true,
      deps: [KeycloakService],
    }
  ],
  bootstrap: [AppComponent]
})
export class AppModule{ 
}*/

@NgModule({
  declarations: [
    AppComponent
  ],
  imports: [
    KeycloakAngularModule,
    BrowserModule,
    AppRoutingModule
  ],
  providers: [
    {
      provide: KeycloakService,
      useValue: keycloakService,
    },
  ],
  entryComponents: [AppComponent],
})
export class AppModule implements DoBootstrap {
  ngDoBootstrap(appRef: ApplicationRef) {
    console.log("environment.keycloak",environment.keycloak);
    keycloakService
      .init({
        config: environment.keycloak,
        initOptions: {
          onLoad: 'login-required',
          checkLoginIframe: false,
        },
        enableBearerInterceptor: true,
        //bearerExcludedUrls: ['/assets', '/clients/public']
      })
      .then(() => {
        console.log('[ngDoBootstrap] bootstrap app');

        appRef.bootstrap(AppComponent);
      })
      .catch((error) =>
        console.error('[ngDoBootstrap] init Keycloak failed', error)
      );
  }
}
