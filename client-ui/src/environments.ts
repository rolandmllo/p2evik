const clientUiUrl = 'http://localhost:4200';
const keyCloakUrl = 'http://localhost:8083';
const backendUrl = 'http://localhost:8081';

export const environment = {
    production: false,
    clientUiUrl: clientUiUrl,
    keyCloakUrl: keyCloakUrl,
    backendUrl: backendUrl,
    apiPrefix: '/api',

    apiEndpoints: {
        users: '/users',
        studyGroups: '/study-groups',
    },

    keycloak: {
        url: keyCloakUrl,
        redirectUri: clientUiUrl,
        postLogoutRedirectUri: clientUiUrl + '/logout',
        realm: 'p2evik',
        clientId: 'p2evik-ui',
    },
    idleConfig: { idle: 10, timeout: 60, ping: 10 },
};