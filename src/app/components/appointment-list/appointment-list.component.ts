import { Component, OnInit } from '@angular/core';
import { AppointmentService } from 'src/app/_services/appointment.service';
import { Appointment } from 'src/app/common/appointment';
import { TokenStorageService } from 'src/app/_services/token-storage.service';
import { Token } from '@angular/compiler/src/ml_parser/lexer';
import { HttpClient } from '@angular/common/http';
import { Router, ActivatedRoute } from '@angular/router';

@Component({
  selector: 'app-appointment-list',
  templateUrl: './appointment-list.component.html',
  styleUrls: ['./appointment-list.component.css']
})
export class AppointmentListComponent implements OnInit {
  appointments:Appointment[];
  private roles: String[]
  checked:any[]; 
  isEmployee:boolean;

  constructor(private appointmentService: AppointmentService, private tokenStorage: TokenStorageService) { }

  ngOnInit(): void {
    this.getMyAppointments(this.tokenStorage);
  
    
  }


  getMyAppointments(token:TokenStorageService){
    var user =token.getUser();
    this.appointmentService.getMyAppointments(user).subscribe(
      data => { 
        this.appointments = data;
        console.log('ya');
      },
      err => {
        console.log("going through");
        this.appointments = JSON.parse(err.error).message;
      }
    );

  }

  getEmpAppointments(token:TokenStorageService){
    var emp = token.getUser(); 
    this.appointmentService.getEmployeeAppointment(emp).subscribe(
      data => {
        console.log(data); 

        this.appointments= data; 

      },

      err => {
        this.appointments= JSON.parse(err.err).message;
      }
    )
  }


  

}
