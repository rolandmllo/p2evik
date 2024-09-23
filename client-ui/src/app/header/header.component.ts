import { AsyncPipe } from '@angular/common';
import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';
import {HttpClient} from "@angular/common/http";
import {tap} from "rxjs";
import {AuthenticationService} from "../shared/services/authentication.service";

@Component({
    selector: 'app-header',
    templateUrl: './header.component.html',
    standalone: true,
    imports: [RouterLink, AsyncPipe],
})
export class HeaderComponent {
    authenticationService: AuthenticationService

    constructor(private http: HttpClient, authenticationService: AuthenticationService) {
        this.authenticationService = authenticationService;
    }

    fetchUsers() {
        console.log(this.authenticationService.userName)

        const a =      this.http.get('http://localhost:8081/users').pipe(
            tap(data => console.log(data))
        ).subscribe();
        console.log(a)
    }
}

