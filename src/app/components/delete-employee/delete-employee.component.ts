import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/_services/user.service';
import { Employee } from 'src/app/common/employee';

@Component({
  selector: 'app-delete-employee',
  templateUrl: './delete-employee.component.html',
  styleUrls: ['./delete-employee.component.css']
})
export class DeleteEmployeeComponent implements OnInit {

  form:any={};
  employee:Employee;
  errorMessage='';
  constructor(private userService:UserService) { }

  ngOnInit(): void {
  }

  onSubmit(){

        this.userService.deleteEmployee(this.form.email).subscribe(
          data=>{
            console.log(data);
          },
          err => {
            this.errorMessage = err.error.message;
            console.log("secure");
          });

      }
      
    
    

  }

