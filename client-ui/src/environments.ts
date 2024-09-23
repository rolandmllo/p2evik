export const host = 'http://localhost:4200';

export const environment = {
    production: false,
    keycloak: {
        url: 'http://localhost:8083',
        redirectUri: host,
        postLogoutRedirectUri: host + '/logout',
        realm: 'p2evik',
        clientId: 'p2evik-ui',
    },
    idleConfig: { idle: 10, timeout: 60, ping: 10 },
};