import { ComponentFixture, TestBed } from '@angular/core/testing';

import { SupplierVerificationComponent } from './supplier-verification.component';

describe('SupplierVerificationComponent', () => {
  let component: SupplierVerificationComponent;
  let fixture: ComponentFixture<SupplierVerificationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [SupplierVerificationComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(SupplierVerificationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
