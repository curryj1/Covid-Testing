import { Component, OnInit } from '@angular/core';
import { AppointmentService } from 'src/app/_services/appointment.service';

@Component({
  selector: 'app-update-appointment',
  templateUrl: './update-appointment.component.html',
  styleUrls: ['./update-appointment.component.css'],
})
export class UpdateAppointmentComponent implements OnInit {
  form: any = {};
  errorMessage = '';
  isSuccessful = false;
  isSignUpFailed = false;
  constructor(private appointmentService: AppointmentService) {}

  ngOnInit(): void {}

  onSubmit(): void {
    this.appointmentService.setResult(this.form).subscribe(
      (data) => {
        data = this.form;
        console.log(data);
      },
      (err) => {
        this.errorMessage = err.error.message;
        this.isSuccessful = true;
        this.isSignUpFailed = false;
      }
    );
  }
}
