import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { User } from '../model/user';
import { UserService } from '../service/user.service';

@Component({
    selector: 'app-signup',
    templateUrl: './signup.component.html',
    styleUrls: ['./signup.component.scss'],
    providers: [UserService]
})
export class SignupComponent implements OnInit {
    model: any = {};
    _users: User;
    constructor(private _userService: UserService,
        private _router: Router) { }

    ngOnInit() { }

    registerUser() {
        const user: User = new User();
        this._splitName(this.model.name);
        user.firstName = this.model.firstName;
        user.middleName = this.model.middleName;
        user.lastName = this.model.lastName;
        user.phone = this.model.phone;
        user.email = this.model.email;
        user.password = this.model.password;

        let _response: any;
        // Get all comments
        this._userService.addUser(user)
            .subscribe(
            response => {
                _response = response;
                alert('Congratualtions! You have registered successfully');
                this._router.navigate(['/login']);
            },
            err => {
                // Log errors if any
                console.log(err);
            });
    }

    private _splitName(name: string) {
        const tmp = name.split(' ');
        this.model.firstName = tmp[0];
        this.model.lastName = tmp[tmp.length - 1];
        if (tmp.length === 2) {
            this.model.middleName = '';
        } else if (tmp.length >= 3) {
            let middleName = '';
            for (let i = 1; i < tmp.length - 1; i++) {
                middleName = middleName + tmp[i] + ' ';
            }
            middleName.trim();
            this.model.middleName = middleName;
        }
    }
}
