import { CanActivateFn, Router, UrlTree } from '@angular/router';
import { inject } from '@angular/core';
import { KeycloakService } from 'keycloak-angular';

export const LogoutGuard: CanActivateFn = async (): Promise<boolean | UrlTree> => {
    const router = inject(Router);
    const keycloakService = inject(KeycloakService);

    const authenticated: boolean = keycloakService.isLoggedIn();

    if (authenticated) {
        return router.createUrlTree(['/']);
    }

    return true;
};