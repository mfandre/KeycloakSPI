import { async, ComponentFixture, TestBed } from '@angular/core/testing';

import { OtherComponentComponent } from './other-component.component';

describe('OtherComponentComponent', () => {
  let component: OtherComponentComponent;
  let fixture: ComponentFixture<OtherComponentComponent>;

  beforeEach(async(() => {
    TestBed.configureTestingModule({
      declarations: [ OtherComponentComponent ]
    })
    .compileComponents();
  }));

  beforeEach(() => {
    fixture = TestBed.createComponent(OtherComponentComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
