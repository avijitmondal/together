import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { User } from './model/user';

@Component({
    selector: 'app-root',
    templateUrl: './app.component.html',
    styleUrls: ['./app.component.scss']
})
export class AppComponent implements OnInit {
    private _user: User;

    constructor(private _router: Router) { }
    ngOnInit() {
        if (typeof (Storage) !== 'undefined') {
            this._user = JSON.parse(localStorage.getItem('currentUser'));
            if (this._user !== null) {
                this._router.navigate(['/dashboard']);
            }
        } else {
            console.log('Sorry! No Web Storage support..');
        }
    }
}
