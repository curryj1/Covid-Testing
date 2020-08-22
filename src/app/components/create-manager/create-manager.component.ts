import { Component, OnInit } from '@angular/core';
import { AuthService } from 'src/app/_services/auth.service';
import { TokenStorageService } from 'src/app/_services/token-storage.service';

@Component({
  selector: 'app-create-manager',
  templateUrl: './create-manager.component.html',
  styleUrls: ['./create-manager.component.css']
})
export class CreateManagerComponent implements OnInit {

  constructor(private authService: AuthService, private token:TokenStorageService) { }
  form: any = {};
  isSuccessful = false;
  isSignUpFailed = false;
  errorMessage = '';
  ngOnInit(): void {
    var user = this.token.getUser();
    console.log(user);
  }
  
  onSubmit(): void {
    this.authService.createManager(this.form).subscribe(
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
