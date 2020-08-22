import { Component, OnInit } from '@angular/core';
import { Center} from '../../common/center';
import { UserService } from 'src/app/_services/user.service';
import { CenterService } from 'src/app/_services/center.service';

@Component({
  selector: 'app-center-list',
  templateUrl: './center-list.component.html',
  styleUrls: ['./center-list.component.css']
})
export class CenterListComponent implements OnInit {
centers: Center[];
  constructor(private userService: UserService, private centerService: CenterService) { }

  ngOnInit(): void {

    this.centerService.getAllCenters().subscribe(
      data => {
        this.centers = data;
      },
      err => {
        this.centers = JSON.parse(err.error).message;
      }
    );
  }

}
