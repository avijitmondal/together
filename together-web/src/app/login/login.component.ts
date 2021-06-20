import { Component, OnInit } from '@angular/core';
import { User } from '../model/user';
import { AuthenticationService } from '../service/authentication.service';

@Component({
    selector: 'app-login',
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.scss'],
    providers: [AuthenticationService]
})
export class LoginComponent implements OnInit {
    private _user: User;
    private model: any = {};

    constructor(private _authenticationService: AuthenticationService) { }
    ngOnInit() {
    }

    authenticateUser() {
        this._authenticationService.login(this.model.email, this.model.password);
    }

}
