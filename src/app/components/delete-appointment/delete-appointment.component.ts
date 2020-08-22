import { Component, OnInit } from '@angular/core';
import { Appointment } from 'src/app/common/appointment';
import { AppointmentService } from 'src/app/_services/appointment.service';

@Component({
  selector: 'app-delete-appointment',
  templateUrl: './delete-appointment.component.html',
  styleUrls: ['./delete-appointment.component.css']
})
export class DeleteAppointmentComponent implements OnInit {

  form:any={};
  constructor(private appointmentService:AppointmentService) { }

  ngOnInit(): void {
  }

  onSubmit(){
    this.appointmentService.deleteAppointment(this.form).subscribe(
      data=>{
        console.log(data);
      }
    );

  }

}
