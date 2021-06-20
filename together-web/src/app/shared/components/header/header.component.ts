import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
    selector: 'app-header',
    templateUrl: './header.component.html',
    styleUrls: ['./header.component.scss']
})
export class HeaderComponent implements OnInit {
    constructor(private _router: Router) { }
    ngOnInit() { }

    toggleSidebar() {
        const dom: any = document.querySelector('body');
        dom.classList.toggle('push-right');
    }
    rltAndLtr() {
        const dom: any = document.querySelector('body');
        dom.classList.toggle('rtl');
    }

    onLogout() {
        if (typeof (Storage) !== 'undefined') {
            localStorage.removeItem('currentUser');
        } else {
            console.log('Sorry! No Web Storage support..');
        }
        this._router.navigate(['']);
    }
}
