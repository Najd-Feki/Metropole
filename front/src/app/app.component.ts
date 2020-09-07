import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import { Router } from '@angular/router';
import { Title } from '@angular/platform-browser';
import { AppState } from './app.service';
import { environment } from '../environments/environment';
import { AuthService } from './auth/authService';
import { LoginComponent } from './components/login/login.component';
import { AuthController } from './ducks/auth/auth.controller';
//LoginComponent
@Component({
    selector: 'app-component',
    templateUrl: './app.component.html',
    encapsulation: ViewEncapsulation.None,
})
export class AppComponent implements OnInit {
    public name = 'Metropole';
    public openSidebar = false;
    public openNotifications = false;
    public username;


    constructor(
        public _appState: AppState,
        private _titleService: Title,
        private _router: Router,
        private _authenticationService: AuthService,
        private _authenticationController: AuthController
    ) {}
    /**
     * [name description]
     * @type {[type]}
     */
    public ngOnInit() {
        this.name = environment.name;
        this._titleService.setTitle(`${environment.name} Admin`);
        console.log('Initial App State', this._appState.state);
        this._authenticationController.getUsername().subscribe(username=>this.username=username);
        console.log(this.username);
        
        
    }

    /**
     * [e description]
     * @type {[type]}
     */
    public logout(e: MouseEvent) {
        e.preventDefault();
        this.openSidebar = false;
        this._authenticationService.removeToken();
        this._router.navigate(['/']);
    }
}
