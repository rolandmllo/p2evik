import {AsyncPipe, NgIf} from '@angular/common';
import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';
import {AuthenticationService} from "../../../core/services/authentication.service";
import {MatToolbar} from "@angular/material/toolbar";
import {MatButton, MatFabButton} from "@angular/material/button";

@Component({
    selector: 'app-header',
    templateUrl: './header.component.html',
    styleUrl: './header.component.scss',
    standalone: true,
    imports: [RouterLink, AsyncPipe, MatToolbar, MatFabButton, MatButton, NgIf],
})
export class HeaderComponent {
    authenticationService: AuthenticationService

    constructor(authenticationService: AuthenticationService) {
        this.authenticationService = authenticationService;
    }
}

