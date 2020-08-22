import { Component, OnInit } from '@angular/core';
import { CenterService } from 'src/app/_services/center.service';
import { TokenStorageService } from 'src/app/_services/token-storage.service';
import { Center } from 'src/app/common/center';

@Component({
  selector: 'app-update-center',
  templateUrl: './update-center.component.html',
  styleUrls: ['./update-center.component.css']
})
export class UpdateCenterComponent implements OnInit {

  form: any = {};
  id: any={};
  isSuccessful = false;
  center:Center;
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

    this.centerService.getByName(this.id).subscribe(
      info=>{
        this.center=info;
        console.log(this.center);
        
        this.centerService.updateCenter(this.form,this.center.id).subscribe(
          data => {
            console.log("this doesn't print");
            this.isSuccessful = true;
            this.isSignUpFailed = false;
          }
        );

        
      },
      err => {
        this.errorMessage = err.error.message;
        this.isSignUpFailed = true;
      }
    );
  }

}
