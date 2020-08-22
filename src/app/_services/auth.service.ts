import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import { tokenName } from '@angular/compiler';

const AUTH_API = 'http://localhost:8080/';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  constructor(private http: HttpClient) { }

  login(credentials): Observable<any> {
    return this.http.post(AUTH_API + 'user/'+'signin', {
      email: credentials.email,
      password: credentials.password
    }, httpOptions);
  }

  register(user): Observable<any> {
    return this.http.post(AUTH_API+'user/' + 'signup', {
      email: user.email,
      firstName: user.firstName,
      lastName: user.lastName,
      password: user.password
    }, httpOptions);
  }
  Eregister(token,user): Observable<any> {
    return this.http.post(AUTH_API+'employee/' + 'signup', {
      email: user.email,
      firstName: user.firstName,
      lastName: user.lastName,
      password: user.password,
      role:user.role,
      center:token.center
    }, httpOptions);
  }

  createManager(user): Observable<any>{
    return this.http.post(AUTH_API+ 'employee/signup/MANAGER',{
      email:user.email,
      firstName:user.firstName,
      lastName:user.lastName,
      password: user.password,
      center:user.center
    }, httpOptions)
  }


  employeeLogin(credentials):Observable<any> {
    return this.http.post(AUTH_API + 'employee/'+'signin', {
      email: credentials.email,
      password: credentials.password
    }, httpOptions);
 
  }
}