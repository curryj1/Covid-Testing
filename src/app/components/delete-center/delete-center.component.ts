import { Component, OnInit } from '@angular/core';
import { CenterService } from 'src/app/_services/center.service';
import { Center } from 'src/app/common/center';

@Component({
  selector: 'app-delete-center',
  templateUrl: './delete-center.component.html',
  styleUrls: ['./delete-center.component.css']
})
export class DeleteCenterComponent implements OnInit {

  form:any={};
  center:Center;
  errorMessage='';
  constructor(private centerService:CenterService) { }

  ngOnInit(): void {
  }

  onSubmit(){

  this.centerService.getByName(this.form).subscribe(
      info=>{
        this.center=info;
        console.log(this.center);

        this.centerService.deleteCenter(this.center).subscribe(
          data=>{
            console.log(data);
          }
        );
      },
      err => {
        this.errorMessage = err.error.message;
        console.log("secure");
      })
    
    

  }
}
