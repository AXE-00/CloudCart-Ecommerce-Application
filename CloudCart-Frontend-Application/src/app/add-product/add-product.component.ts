import { CommonModule } from '@angular/common';
import { Component, OnInit } from '@angular/core';
import { FormBuilder, FormsModule, ReactiveFormsModule, Validators } from '@angular/forms';
import { ProductService } from '../service/product.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { UserService } from '../service/user.service';

@Component({
  selector: 'app-add-product',
  standalone: true,
  imports: [FormsModule,ReactiveFormsModule,CommonModule],
  templateUrl: './add-product.component.html',
  styleUrl: './add-product.component.css'
})
export class AddProductComponent implements OnInit{

  userEmail:any;

  constructor(
        private fb:FormBuilder,
        private productService:ProductService,
        private _snackBar:MatSnackBar,
        private userSer:UserService
        ){}

  ngOnInit(): void {
    this.userSer.getUserData().subscribe({
      next:(data:any)=>{
        this.userEmail=data.userEmail;//getting
      }
    })
  }

  productForm = this.fb.group({
    category:["",Validators.required],
    productName:["",Validators.required],
    productPrice:["",Validators.required],
    quantity:["",Validators.required],
    imageUrl:["",Validators.required],
    productRating:["",Validators.required],
    description:["",Validators.required],
  })


  getProductId(){
    return this.productForm.get('productId')?.value;
  }

  onSubmit(){
    this.productService.addProduct(this.productForm.value).subscribe({
      next:data =>{
        this._snackBar.open('Product added successfully.....', 'success', {
          duration: 2000,
          panelClass: ['mat-toolbar', 'mat-primary']
        });
      },
      error:err=>{
        this._snackBar.open('Product is not added.....', 'Failure', {
          duration: 2000,
          panelClass: ['mat-toolbar', 'mat-primary']
        });
      }
    })
  }
}
