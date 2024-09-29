import {Component, OnInit} from '@angular/core';
import {KeycloakService} from "keycloak-angular";
import {ActivatedRoute} from "@angular/router";

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [],
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})
export class LoginComponent implements OnInit {

  constructor(private keycloakService: KeycloakService,
              private route: ActivatedRoute
  ) {}

  async ngOnInit(): Promise<void> {
    const redirectUrl = this.route.snapshot.queryParamMap.get('redirectUrl') || '';
    const redirectUri = window.location.origin + redirectUrl;
    await this.keycloakService.login({ redirectUri });
  }
}
