import { AsyncPipe } from '@angular/common';
import { Component, inject } from '@angular/core';
import { RouterLink } from '@angular/router';
import {SecurityStore} from "../shared/security";
import {HttpClient} from "@angular/common/http";
import {tap} from "rxjs";

@Component({
    selector: 'app-header',
    templateUrl: './header.component.html',
    standalone: true,
    imports: [RouterLink, AsyncPipe],
})
export class HeaderComponent {
    securityStore = inject(SecurityStore);
    user = this.securityStore.user;

    constructor(private http: HttpClient) {
        console.log(this.securityStore.loadedUser());
        this.http.get('http://localhost:8081/api/users').pipe(
            tap(data => console.log(data))
        ).subscribe();
    }
}

