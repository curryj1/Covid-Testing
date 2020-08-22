import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';

import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';

import { BoardAdminComponent } from './components/board-admin/board-admin.component';
import { BoardModeratorComponent } from './components/board-moderator/board-moderator.component';
import {FormsModule} from '@angular/forms';
import { HttpClientModule} from '@angular/common/http';
import { authInterceptorProviders } from './_helpers/auth.interceptor';
import { CenterListComponent } from './components/center-list/center-list.component';
import { AppointmentListComponent } from './components/appointment-list/appointment-list.component';
import { AppointmentRegisterComponent } from './components/appointment-register/appointment-register.component';
import { EmployeeLoginComponent } from './components/employee-login/employee-login.component';
import { CenterAppointmentsComponent } from './components/center-appointments/center-appointments.component';
import { EmployeeRegisterComponent } from './components/employee-register/employee-register.component';
import { DeleteAppointmentComponent } from './components/delete-appointment/delete-appointment.component';
import { CreateManagerComponent } from './components/create-manager/create-manager.component';
import { EmployeeListComponent } from './components/employee-list/employee-list.component';
import { BoardManagerComponent } from './components/board-manager/board-manager.component';
import { CovidComponent } from './components/covid/covid.component';
import { DeleteCenterComponent } from './components/delete-center/delete-center.component';
import { UpdateCenterComponent } from './components/update-center/update-center.component';
import { RemoteAppointmentComponent } from './components/remote-appointment/remote-appointment.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { HomeComponent } from './home/home.component';
import { ProfileComponent } from './profile/profile.component';
import { DeleteEmployeeComponent } from './components/delete-employee/delete-employee.component';



@NgModule({
  declarations: [
    AppComponent,
    LoginComponent,
    RegisterComponent,
    HomeComponent,
    ProfileComponent,
    BoardAdminComponent,
    BoardModeratorComponent,
    CenterListComponent,
    AppointmentListComponent,
    AppointmentRegisterComponent,
    EmployeeLoginComponent,
    CenterAppointmentsComponent,
    EmployeeRegisterComponent,
    DeleteAppointmentComponent,
    CreateManagerComponent,
    EmployeeListComponent, 
    BoardManagerComponent, CovidComponent, DeleteCenterComponent, UpdateCenterComponent, RemoteAppointmentComponent, DeleteEmployeeComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule, 
    FormsModule, 
    HttpClientModule, 
  ],
  providers: [authInterceptorProviders],
  bootstrap: [AppComponent]
})
export class AppModule { }
