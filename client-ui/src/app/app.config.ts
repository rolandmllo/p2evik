import {APP_INITIALIZER, ApplicationConfig, provideZoneChangeDetection} from '@angular/core';
import {provideRouter} from '@angular/router';

import {routes} from './app.routes';
import {
    HTTP_INTERCEPTORS,
    provideHttpClient, withFetch,
    withInterceptorsFromDi
} from "@angular/common/http";
import {KeycloakAngularModule, KeycloakBearerInterceptor, KeycloakOptions, KeycloakService} from "keycloak-angular";
import {environment} from "../environments";
import { provideAnimationsAsync } from '@angular/platform-browser/animations/async';

export function initializer(keycloak: KeycloakService): () => Promise<boolean> {
    const options: KeycloakOptions = {
        config: environment.keycloak,
        initOptions: {
            onLoad: 'check-sso',
            silentCheckSsoRedirectUri: window.location.origin + "/assets/silent-check-sso.html",
            checkLoginIframe: true,
        },
        enableBearerInterceptor: true,
        bearerPrefix: "Bearer",
        loadUserProfileAtStartUp: false,
        bearerExcludedUrls: ["/assets"],
    };

    return () => keycloak.init(options);
}

export const appConfig: ApplicationConfig = {
    providers: [provideZoneChangeDetection({eventCoalescing: true}),
        provideRouter(routes),
        {
            provide: APP_INITIALIZER,
            useFactory: initializer,
            multi: true,
            deps: [KeycloakService]
        },
        {
            provide: HTTP_INTERCEPTORS,
            useClass: KeycloakBearerInterceptor,
            multi: true
        },
        KeycloakService,
        KeycloakAngularModule,
        provideHttpClient(withInterceptorsFromDi(), withFetch()), provideAnimationsAsync(),
    ],
};
