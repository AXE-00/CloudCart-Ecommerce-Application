import { ComponentFixture, TestBed } from '@angular/core/testing';

import { ProductDashComponent } from './product-dash.component';

describe('ProductDashComponent', () => {
  let component: ProductDashComponent;
  let fixture: ComponentFixture<ProductDashComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [ProductDashComponent]
    })
    .compileComponents();
    
    fixture = TestBed.createComponent(ProductDashComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
