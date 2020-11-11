import { Component, OnInit } from '@angular/core';
import { KeycloakService } from 'keycloak-angular';

@Component({
  selector: 'app-some-component',
  templateUrl: './some-component.component.html',
  styleUrls: ['./some-component.component.css']
})
export class SomeComponentComponent implements OnInit {

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
