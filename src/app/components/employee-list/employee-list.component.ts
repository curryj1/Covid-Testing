import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/_services/user.service';
import { Users } from 'src/app/common/users';

@Component({
  selector: 'app-employee-list',
  templateUrl: './employee-list.component.html',
  styleUrls: ['./employee-list.component.css']
})
export class EmployeeListComponent implements OnInit {
  users: Users[];
  constructor(private userService: UserService) { }

  ngOnInit(): void {

    this.userService.getEmployees().subscribe(
      data => {
        this.users = data;
      },
      err => {
        this.users = JSON.parse(err.error).message;
      }
    );
  }
}
