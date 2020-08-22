import { Component, OnInit } from '@angular/core';
import { AppointmentService } from 'src/app/_services/appointment.service';
import { TokenStorageService } from 'src/app/_services/token-storage.service';
import { analyzeAndValidateNgModules } from '@angular/compiler';

@Component({
  selector: 'app-appointment-register',
  templateUrl: './appointment-register.component.html',
  styleUrls: ['./appointment-register.component.css']
})
export class AppointmentRegisterComponent implements OnInit {

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
   

    this.appointmentService.register(this.form,user).subscribe(
      
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
