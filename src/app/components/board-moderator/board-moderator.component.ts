import { Component, OnInit } from '@angular/core';
import { UserService } from '../../_services/user.service';
import { Users } from '../../common/users';

@Component({
  selector: 'app-board-user',
  templateUrl: './board-moderator.component.html',
  styleUrls: ['./board-moderator.component.css']
})
export class BoardModeratorComponent implements OnInit {
//why do I want a list of Users for this page?
  users:Users[];
  content: string;

  constructor(private userService: UserService) { }

  ngOnInit(): void {
    this.userService.getUsers().subscribe(
      data => {
        this.users = data;
      },
      err => {
        this.content = JSON.parse(err.error).message;
      }
    );
  }
}
