import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import {Country} from '../common/country'

const API_URL = 'https://api.covid19api.com/';

const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};

@Injectable({
  providedIn: 'root'
})

export class CovidService {

  constructor(private http: HttpClient) { }

  getByCountry(country): Observable<any>{
    return this.http.get(API_URL + 'total/country/'+country, { responseType: 'json' });

  }

  listCountries():Observable<any>{
    return this.http.get<CountryResponse>(API_URL + 'countries', { responseType: 'json' });

    
  }


}

interface CountryResponse{
  countries:Country[];
}