import { NgModule, Component } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { CenterListComponent } from './components/center-list/center-list.component';
import { BoardModeratorComponent } from './components/board-moderator/board-moderator.component';
import { AppointmentListComponent } from './components/appointment-list/appointment-list.component';
import { AppointmentRegisterComponent } from './components/appointment-register/appointment-register.component';
import { EmployeeLoginComponent } from './components/employee-login/employee-login.component';
import { CenterAppointmentsComponent } from './components/center-appointments/center-appointments.component';
import { EmployeeRegisterComponent } from './components/employee-register/employee-register.component';
import { CreateManagerComponent } from './components/create-manager/create-manager.component';
import { CommonModule } from '@angular/common';
import { CovidComponent } from './components/covid/covid.component';
import { DeleteAppointmentComponent } from './components/delete-appointment/delete-appointment.component';
import { DeleteCenterComponent } from './components/delete-center/delete-center.component';
import { UpdateCenterComponent } from './components/update-center/update-center.component';
import { RemoteAppointmentComponent } from './components/remote-appointment/remote-appointment.component';
import { LoginComponent } from './login/login.component';
import { BoardManagerComponent } from './components/board-manager/board-manager.component';
import { RegisterComponent } from './register/register.component';
import { ProfileComponent } from './profile/profile.component';
import { HomeComponent } from './home/home.component';
import { BoardAdminComponent } from './components/board-admin/board-admin.component';
import { EmployeeListComponent } from './components/employee-list/employee-list.component';
import { DeleteEmployeeComponent } from './components/delete-employee/delete-employee.component';
import { UpdateAppointmentComponent } from './components/update-appointment/update-appointment.component';

const routes: Routes = [
  { path: 'mod/update', component: UpdateAppointmentComponent },
  { path: 'appointment', component: AppointmentListComponent },
  { path: 'appointment/register', component: AppointmentRegisterComponent },
  { path: 'admin', component: BoardAdminComponent },
  { path: 'mod', component: BoardModeratorComponent },
  { path: 'manager/center', component: CenterAppointmentsComponent },
  { path: 'mod/center', component: CenterAppointmentsComponent },
  { path: 'index', component: CenterListComponent },
  { path: 'covid', component: CovidComponent },
  { path: 'manager/create/manager', component: CreateManagerComponent },
  { path: 'mod/delete', component: DeleteAppointmentComponent },
  { path: 'appointment/delete', component: DeleteAppointmentComponent },
  { path: 'admin/center/delete', component: DeleteCenterComponent },
  { path: 'manager/delete', component: DeleteEmployeeComponent },
  { path: 'manager/employees', component: EmployeeListComponent },
  { path: 'login/employee', component: EmployeeLoginComponent },
  { path: 'manager/register', component: EmployeeRegisterComponent },
  { path: 'mod/remote/add', component: RemoteAppointmentComponent },
  { path: 'admin/center/update', component: UpdateCenterComponent },
  { path: 'admin/center/add', component: HomeComponent },
  { path: 'login', component: LoginComponent },
  { path: 'manager', component: BoardManagerComponent },
  { path: 'profile', component: ProfileComponent },
  { path: 'register', component: RegisterComponent },
  { path: '', redirectTo: 'index', pathMatch: 'full' },
];

@NgModule({
  imports: [RouterModule.forRoot(routes), CommonModule],
  exports: [RouterModule],
})
export class AppRoutingModule {}
