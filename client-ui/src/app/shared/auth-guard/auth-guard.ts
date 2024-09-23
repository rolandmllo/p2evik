import {CanMatchFn, Router, UrlTree} from "@angular/router";
import {inject} from "@angular/core";
import { KeycloakService} from "keycloak-angular";

export const authGuard: CanMatchFn = async (route, segments): Promise<boolean | UrlTree> => {
    const router = inject(Router);
    const keycloakService = inject(KeycloakService);

    const authenticated: boolean = keycloakService.isLoggedIn();

    if (!authenticated) {
        await keycloakService.login({
            redirectUri: window.location.origin,
        });
    }

    const roles: string[] = keycloakService.getUserRoles(true);
    const requiredRoles = route.data?.['roles'];

    if (!Array.isArray(requiredRoles) || requiredRoles.length === 0) {
        return true;
    }

    const authorized = requiredRoles.every((role) => roles.includes(role));

    if (authorized) {
        return true;
    }

    return router.createUrlTree(['/access']);
};