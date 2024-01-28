import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SupplierWaitingComponent } from './supplier-waiting.component';

describe('SupplierWaitingComponent', () => {
  let component: SupplierWaitingComponent;
  let fixture: ComponentFixture<SupplierWaitingComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SupplierWaitingComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(SupplierWaitingComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
