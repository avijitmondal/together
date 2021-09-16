import { Injectable } from '@angular/core';
import { Http, Headers, RequestOptions, Response } from '@angular/http';
import { Router } from '@angular/router';

import 'rxjs/add/operator/map';
import 'rxjs/add/operator/toPromise';

@Injectable()
export class AuthenticationService {
    private _url = 'http://192.168.2.187:8080/together/api/authenticate';
    constructor(private _http: Http,
        private _router: Router) { }

    login(email: string, password: string) {
        const headers = new Headers({ 'Content-Type': 'application/json' });
        const options = new RequestOptions({ headers: headers });

        this._http.post(this._url, { email: email, password: password }, options)
            .subscribe(response => {
                if (response.status === 200) {
                    const usr: any = {};
                    usr.email = email;
                    usr.password = password;
                    localStorage.setItem('currentUser', JSON.stringify(usr));
                    this._router.navigate(['/dashboard']);
                } else {
                    alert(response.text);
                    console.log(response.text);
                }
            },
            error => {
                alert(error.text());
                console.log(error.text());
            });
    }

    logout() {
        // remove user from local storage to log user out
        localStorage.removeItem('currentUser');
    }
}
