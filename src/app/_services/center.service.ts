import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';
import {Center} from '../common/center';
import { TokenStorageService } from './token-storage.service';

const API_URL = 'http://localhost:8080/center';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

 

@Injectable({
  providedIn: 'root'
})
export class CenterService {

  constructor(private http: HttpClient, private tokenStorage:TokenStorageService) { }


  getAllCenters(): Observable<any> {
    return this.http.get<CenterResponse>(API_URL + '/all', { responseType: 'json' });


    interface CenterResponse {
        centers: Center[];
      
    }
  }

  registerCenter(center): Observable<any> {
    return this.http.post(API_URL+'/add', {
      name: center.name,
      streetAddress: center.streetAddress,
      city: center.city,
      state: center.state,
      zipcode: center.zipcode,
      phoneNumber: center.phoneNumber
    }, httpOptions) ;
  }

  getByName(form):Observable<any>{
    console.log(form);
    return this.http.get(API_URL +'/'+ form.name, {responseType:'json' })
  }
  deleteCenter(center):Observable<any>{
    console.log(center);
    return this.http.delete(API_URL+'/delete/'+center.id,httpOptions);
  }

  updateCenter(center, id):Observable<any>{
    return this.http.put(API_URL+'/update/'+id,{
      name: center.name,
      streetAddress: center.streetAddress,
      city: center.city,
      state: center.state,
      zipcode: center.zipcode,
      phoneNumber: center.phoneNumber
    }, httpOptions);
  }


  }

