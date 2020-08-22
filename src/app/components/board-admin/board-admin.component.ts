import { Component, OnInit } from '@angular/core';
import { UserService } from 'src/app/_services/user.service';
import { TokenStorageService } from 'src/app/_services/token-storage.service';


@Component({
  selector: 'app-board-admin',
  templateUrl: './board-admin.component.html',
  styleUrls: ['./board-admin.component.css']
})
export class BoardAdminComponent implements OnInit {

  content: string;
  firstName:string;
  lastName:string;

  constructor(private token:TokenStorageService) { }

  ngOnInit(): void {
    this.firstName= this.token.getUser().firstName;
    this.lastName= this.token.getUser().lastName;
    
  }

}