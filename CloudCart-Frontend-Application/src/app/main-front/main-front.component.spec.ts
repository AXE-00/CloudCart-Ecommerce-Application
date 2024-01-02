import { ComponentFixture, TestBed } from '@angular/core/testing';

import { MainFrontComponent } from './main-front.component';

describe('MainFrontComponent', () => {
  let component: MainFrontComponent;
  let fixture: ComponentFixture<MainFrontComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [MainFrontComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(MainFrontComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
