import { Injectable } from '@angular/core';
import { HttpHeaders, HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Employee } from '../common/employee';
import { Patient } from '../common/patient';
import { Center } from '../common/center';




const httpOptions = {
  headers: new HttpHeaders({ 'Content-Type': 'application/json' })
};




@Injectable({
  providedIn: 'root'
})
export class AppointmentService {

  private AUTH_API = 'http://localhost:8080/appointment/';



  constructor(private http: HttpClient) { }

  getAllAppointments(): Observable<any> {
    console.log("getAllAppiontment Call");
    return this.http.get<AppointmentResponse>(this.AUTH_API + 'all', { responseType: 'json' });
  }

  getMyAppointments(person: Patient): Observable<any> {
    var data = this.http.get<AppointmentResponse>(this.AUTH_API + person.email, { responseType: 'json' });
    console.log(data);
    return data;
  }

  getEmployeeAppointment(person: Employee): Observable<any> {

    return this.http.get<AppointmentResponse>(this.AUTH_API +'employee/'+ person.email, { responseType: 'json' });
  }

  register(appointment, user ): Observable < any > {

    
    return this.http.post(this.AUTH_API, {
      email: user.email,
      center: appointment.center,
      result: appointment.rsult,
      date: appointment.date,
      time: appointment.time
    }, httpOptions);
  
  }

  RemoteRegister(appointment): Observable < any > {

    
    return this.http.post(this.AUTH_API, {
      email: appointment.email,
      center: appointment.center,
      result: appointment.rsult,
      date: appointment.date,
      time: appointment.time
    }, httpOptions);
  
  }

  getByCenter(center): Observable<any> {
    console.log('this is center id:' +center);
    return this.http.get<AppointmentResponse>(this.AUTH_API + 'center/' + center.id, { responseType: 'json' });
  }

  deleteAppointment(appointment): Observable<any>{
    return this.http.delete(this.AUTH_API+'delete/'+appointment.id,httpOptions);
  }
}



interface AppointmentResponse {
  appointments: AppointmentService[];


}
