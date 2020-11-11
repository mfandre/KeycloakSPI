import { Component, OnInit } from '@angular/core';
import { KeycloakService } from 'keycloak-angular';

@Component({
  selector: 'app-other-component',
  templateUrl: './other-component.component.html',
  styleUrls: ['./other-component.component.css']
})
export class OtherComponentComponent implements OnInit {

  constructor(protected keycloakAngular: KeycloakService) { }

  ngOnInit() {
    try {
      let userDetails = this.keycloakAngular.getKeycloakInstance().tokenParsed["userDetails"];
      console.log("userDetails", userDetails);
    } catch (e){
      console.log('Failed to load user details', e);
    }
  }

}
