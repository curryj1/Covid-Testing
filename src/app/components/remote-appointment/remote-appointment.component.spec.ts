import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { RemoteAppointmentComponent } from './remote-appointment.component';

describe('RemoteAppointmentComponent', () => {
  let component: RemoteAppointmentComponent;
  let fixture: ComponentFixture<RemoteAppointmentComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ RemoteAppointmentComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(RemoteAppointmentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
