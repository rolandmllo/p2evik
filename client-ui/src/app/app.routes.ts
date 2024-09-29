import { Routes } from '@angular/router';
import {StudyGroupComponent} from "./features/study-group/components/study-group.component";
import {AuthGuard} from "./core/auth-guard/AuthGuard";
import {LoginComponent} from "./shared/components/login/login/login.component";
import {LogoutScreenComponent} from "./shared/components/logout-screen/logout-screen.component";
import {LogoutGuard} from "./core/auth-guard/LogoutGuard";
import {NotFoundComponent} from "./shared/components/not-found/not-found.component";
import {AccessDeniedComponent} from "./shared/components/access-denied/access-denied.component";
import {MainPageComponent} from "./features/main-page/main-page.component";

export const routes: Routes = [
    {
        path: 'study-groups',
        component: StudyGroupComponent,
        canActivate: [AuthGuard]
    },
    {
        path: 'login',
        component: LoginComponent
    },
    {
        path: 'logout',
        component: LogoutScreenComponent,
        canActivate: [LogoutGuard]
    },
    {
        path: 'access-denied',
        component: AccessDeniedComponent
    },
    {
        path: '',
        component: MainPageComponent,
    },
    {
        path: '**',
        component: NotFoundComponent,
    }

];
