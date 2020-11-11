import { Component, OnInit } from '@angular/core';
import { KeycloakService } from 'keycloak-angular';

@Component({
  selector: 'app-not-allow',
  templateUrl: './not-allow.component.html',
  styleUrls: ['./not-allow.component.css']
})
export class NotAllowComponent implements OnInit {

  constructor(protected keycloakAngular: KeycloakService) { }

  ngOnInit() {
    try {
      let userDetails = this.keycloakAngular.getKeycloakInstance().tokenParsed["userDetails"];
      console.log("userDetails", userDetails);
      console.log("keycloakInstance", this.keycloakAngular.getKeycloakInstance());
    } catch (e){
      console.log('Failed to load user details', e);
    }
  }

}
