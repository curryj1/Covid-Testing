import { Component, OnInit } from '@angular/core';
import { AuthService } from '../_services/auth.service';
import { CenterService } from '../_services/center.service';
import { TokenStorageService } from '../_services/token-storage.service';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {

  form: any = {};
  isSuccessful = false;
  isSignUpFailed = false;
  errorMessage = '';
  currentUser: any;
  constructor(private centerService: CenterService, private tokenStorage: TokenStorageService) { }

  ngOnInit(): void {

    if (this.tokenStorage.getToken()) {
      this.currentUser = this.tokenStorage.getUser();
      console.log(this.currentUser.accessToken)
      
    }
    
  }

  onSubmit(): void {
    this.centerService.registerCenter(this.form).subscribe(
      data => {
        console.log("this doesn't print");
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