import { Component, OnInit } from '@angular/core';
import { TokenStorageService } from './_services/token-storage.service';
import { Center } from './common/center';
@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  private roles: string[];
  isLoggedIn = false;
  showAdminBoard = false;
  showManagerBoard = false;
  showModeratorBoard = false;
  showUserBoard= false;
  isEmployee = false;
  isPatient = false; 
  email: string;
  center:Center;

  constructor(private tokenStorageService: TokenStorageService) { }

  ngOnInit(): void {
    this.isLoggedIn = !!this.tokenStorageService.getToken();

    if (this.isLoggedIn) {
      const user = this.tokenStorageService.getUser();
      this.roles = user.roles;

      this.showAdminBoard = this.roles.includes('ROLE_ADMIN');
      this.showManagerBoard= this.roles.includes('ROLE_MANAGER');
      this.showModeratorBoard = this.roles.includes('ROLE_CLERK');
      this.isEmployee= this.roles.includes('CLERK') || this.roles.includes('MANAGER') || this.roles.includes('ADMIN');
      this.isPatient = this.roles.includes('USER');

      this.email = user.email;
    }
  }

  logout(): void {
    this.tokenStorageService.signOut();
    window.location.reload();
  }
}
