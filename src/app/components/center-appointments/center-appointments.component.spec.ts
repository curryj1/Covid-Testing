import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { CenterAppointmentsComponent } from './center-appointments.component';

describe('CenterAppointmentsComponent', () => {
  let component: CenterAppointmentsComponent;
  let fixture: ComponentFixture<CenterAppointmentsComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ CenterAppointmentsComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(CenterAppointmentsComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
