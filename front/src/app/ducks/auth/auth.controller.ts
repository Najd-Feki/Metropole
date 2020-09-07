import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { environment } from '../../../environments/environment';
import { ResponseType } from '@angular/http';
import { Observable } from 'rxjs';

@Injectable()
export class AuthController {
    /**
     * [constructor description]
     * @method  constructor
     * @author jackfiallos
     * @version [version]
     * @date    2017-10-29
     * @param   {HttpClient} public http [description]
     * @return  {[type]} [description]
     */
    constructor(public http: HttpClient ) {}
    public  username : String;
    /**
     * [login description]
     * @method  login
     * @author jackfiallos
     * @version [version]
     * @date    2017-11-09
     * @param   {any} data [description]
     * @return  {[type]} [description]
     */
    public login(data: any) {
        console.log(data)
        this.username=data.username;
        return this.http.post(environment.api_url + '/authenticate', data);
    
    }
    public getUsername() :Observable<any> {
        return this.http.get(environment.api_url + '/username/', {responseType: 'text'});
    }
    public getUser(): Observable<any> {
        return this.http.get(environment.api_url+'/user/');
    }
}
