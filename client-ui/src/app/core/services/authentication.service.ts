import {Injectable} from '@angular/core';
import {KeycloakService} from "keycloak-angular";

@Injectable({
  providedIn: 'root'
})
export class AuthenticationService {

  constructor(private readonly keycloakService: KeycloakService) {
    if (this.isLoggedIn()) {
      this.keycloakService.loadUserProfile()
          .catch(error =>
              console.error('Failed to load user profile', error));
    }
  }

  get userName(): string {
    return this.keycloakService.getUsername();
  }

  async login(): Promise<void> {
    await this.keycloakService.login({
      redirectUri: window.location.origin,
    })
  }

  isLoggedIn(): boolean {
    return this.keycloakService.isLoggedIn();
  }
  logout(): void {
    this.keycloakService.logout().catch(error =>
        console.error('Failed to log out', error));
  }

  redirectToLoginPage(): Promise<void> {
    return this.keycloakService.login();
  }

}
