import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Users } from '../common/users';

const API_URL = 'http://localhost:8080/';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  constructor(private http: HttpClient) { }

  getUsers(): Observable<any> {
    return this.http.get<UserResposne>(API_URL  +'user/all');

  }

  deleteEmployee(email): Observable<any> {
     return this.http.delete<UserResposne>(API_URL+'employee/delete/'+email);
  }

  getEmployees():Observable<any>{
    return this.http.get<UserResposne>(API_URL+'employee/all');
  }

  getUserBoard(): Observable<any> {
    return this.http.get(API_URL + 'all', { responseType: 'text' });
  }

  getModeratorBoard(): Observable<any> {
    return this.http.get(API_URL + 'mod', { responseType: 'text' });
  }

  getAdminBoard(): Observable<any> {
    return this.http.get(API_URL + 'all', { responseType: 'text' });
  }
}

interface UserResposne{
  users:Users[];
}