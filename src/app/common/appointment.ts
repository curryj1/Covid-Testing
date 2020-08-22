import { Time } from '@angular/common';
import { Center } from './center';
import { Employee } from './employee';
import { Patient } from './patient';
import { Users } from '../common/users';

export class Appointment {
    appointment_id:number;
    email:Users;
    center: Center;
    result: string;
    date: Date;
    time: Time;

}
