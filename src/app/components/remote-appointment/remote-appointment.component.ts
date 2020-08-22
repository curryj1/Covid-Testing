import { Component, OnInit } from '@angular/core';
import { AppointmentService } from 'src/app/_services/appointment.service';
import { TokenStorageService } from 'src/app/_services/token-storage.service';

@Component({
  selector: 'app-remote-appointment',
  templateUrl: './remote-appointment.component.html',
  styleUrls: ['./remote-appointment.component.css']
})
export class RemoteAppointmentComponent implements OnInit {

  roles:string[];
  form: any = {};
  isSuccessful = false;
  isSignUpFailed = false;
  errorMessage = '';
  constructor(private appointmentService: AppointmentService, private token:TokenStorageService) { }

  ngOnInit(): void {
  }

  onSubmit(): void {
    var user = this.token.getUser()
    this.roles=user.roles;
   

    this.appointmentService.RemoteRegister(this.form).subscribe(
      
      data => {
        console.log(data);
        this.isSuccessful = true;
        this.isSignUpFailed = false;
      },
      err => {
        this.errorMessage = err.error.message;
        this.isSignUpFailed = true;
      }
    );
  }
}
