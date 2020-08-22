import { Component, OnInit } from '@angular/core';
import { Appointment } from 'src/app/common/appointment';
import { Center } from 'src/app/common/center';
import { AppointmentService } from 'src/app/_services/appointment.service';
import { TokenStorageService } from 'src/app/_services/token-storage.service';


@Component({
  selector: 'app-board-manager',
  templateUrl: './board-manager.component.html',
  styleUrls: ['./board-manager.component.css']
})
export class BoardManagerComponent implements OnInit {
appointments:Appointment[];
center:Center;


constructor(private appointmentService: AppointmentService, private tokenStorage: TokenStorageService) { }

  ngOnInit(): void {
this.getMyAppointments(this.tokenStorage);
  }

  getMyAppointments(token:TokenStorageService){
    var user =token.getUser();
    console.log(user.center.name);
    this.appointmentService.getMyAppointments(user).subscribe(
      data => { 
        this.appointments = data;
        console.log(data);
      },
      err => {
        console.log("going through");
        this.appointments = JSON.parse(err.error).message;
      }
    );

  }
}
