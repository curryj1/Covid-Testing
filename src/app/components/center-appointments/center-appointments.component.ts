import { Component, OnInit } from '@angular/core';
import { AppointmentService } from 'src/app/_services/appointment.service';
import { TokenStorageService } from 'src/app/_services/token-storage.service';
import { Appointment } from 'src/app/common/appointment';

@Component({
  selector: 'app-center-appointments',
  templateUrl: './center-appointments.component.html',
  styleUrls: ['./center-appointments.component.css']
})
export class CenterAppointmentsComponent implements OnInit {

  appointments:Appointment[];
  private roles: String[]

  constructor(private appointmentService: AppointmentService, private tokenStorage: TokenStorageService) { }

  ngOnInit(): void {
    this.getAppointments(this.tokenStorage);
    
  }

  getAppointments(token:TokenStorageService){
    var user =token.getUser();
    this.roles=user.roles;
    console.log(user);
    this.appointmentService.getByCenter(user.center).subscribe(
      data => { 
        this.appointments = data;
      },
      err => {
        console.log("going through");
        this.appointments = JSON.parse(err.error).message;
      }
    );

  }
}
