import { TestBed } from '@angular/core/testing';

import { AuthenticationService } from './authentication.service';
import {KeycloakService} from "keycloak-angular";

describe('AuthenticationService', () => {
  let service: AuthenticationService;
  let keycloakServiceSpy: jasmine.SpyObj<KeycloakService>;

  beforeEach(() => {
    const spy = jasmine.createSpyObj('KeycloakService', ['isLoggedIn', 'getUsername', 'login', 'logout', 'loadUserProfile']);

    TestBed.configureTestingModule({
      providers: [
        AuthenticationService,
        { provide: KeycloakService, useValue: spy }
      ]
    });

    service = TestBed.inject(AuthenticationService);
    keycloakServiceSpy = TestBed.inject(KeycloakService) as jasmine.SpyObj<KeycloakService>;
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });

  it('should return username when user is logged in', () => {
    keycloakServiceSpy.getUsername.and.returnValue('testUser');
    expect(service.userName).toBe('testUser');
  });

  it('should call login method of KeycloakService', async () => {
    await service.login();
    expect(keycloakServiceSpy.login).toHaveBeenCalled();
  });

  it('should return true when user is logged in', () => {
    keycloakServiceSpy.isLoggedIn.and.returnValue(true);
    expect(service.isLoggedIn()).toBeTrue();
  });

  it('should return false when user is not logged in', () => {
    keycloakServiceSpy.isLoggedIn.and.returnValue(false);
    expect(service.isLoggedIn()).toBeFalse();
  });

  it('should call logout method of KeycloakService', () => {
    service.logout();
    expect(keycloakServiceSpy.logout).toHaveBeenCalled();
  });

  it('should redirect to login page', async () => {
    await service.redirectToLoginPage();
    expect(keycloakServiceSpy.login).toHaveBeenCalled();
  });
});
