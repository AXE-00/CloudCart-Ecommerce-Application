import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common'; 
import { ProductService } from '../service/product.service';
import { cartRequest } from '../model/cartRequest';
import { CartService } from '../service/cart.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { UserService } from '../service/user.service';


@Component({
  selector: 'app-product',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './product.component.html',
  styleUrl: './product.component.css'
})
export class ProductComponent implements OnInit {

  productData:any;
  proCategory:string='';
  proSuggested:any=[];
  proId:number=0;
  prod?:boolean;
  previousSize:string='S';
  proPrice?:number;
  userData:any;

  constructor(private proSer:ProductService, private cartSer:CartService, 
    private _snackBar:MatSnackBar, private userSer:UserService){}

  ngOnInit(): void {
    this.proId=this.proSer.productId;
    console.log(this.proId);
    
    this.proSer.getProductById(this.proId).subscribe({
      next:data=>{
        this.productData=data;
        this.proCategory=this.productData.data.category;
        this.proPrice = this.productData.data.productPrice;
        console.log("this is proCat : "+this.proCategory);
        
        console.log(this.productData);
        this.getProductByCat();
      }
    })

    
  }
  getStarsArray(): number[] {
    const rating = this.productData.data.productRating || 0;
    const starsArray: number[] = [];
  
    for (let i = 0; i < rating; i++) {
      starsArray.push(i);
    }
  
    return starsArray;
  }

  getProductByCat(){
   
    this.proSer.getProductByCategory(this.proCategory).subscribe({
     next:(data)=>{
      if(this.proSuggested){
        this.proSuggested=data;
        console.log(this.proSuggested);
      }
     }
    })
    
  }

  addToFavourites(proId:number){

  }

  addProductToCart() {
    this.userSer.getUserData().subscribe({
      next: (data) => {
        this.userData = data; // Assign the fetched user data
  
        // Create the request object after user data is available
        const request: cartRequest = {
          userId: this.userData.id,
          userEmail: this.userData.userEmail,
          productId: this.proId
        };
  
        // Call the service to add the product to the cart
        this.cartSer.addProductInCart(request).subscribe({
          next: (data) => {
            this._snackBar.open('Product added successfully.....', 'success', {
              duration: 2000,
              panelClass: ['mat-toolbar', 'mat-primary']
            });
          },
          error: (err) => {
            this._snackBar.open('Failed to add product', 'error', {
              duration: 2000,
              panelClass: ['mat-toolbar', 'mat-warn']
            });
            console.error('Error adding product to cart:', err);
          }
        });
      },
      error: (err) => {
        this._snackBar.open('Failed to fetch user data', 'error', {
          duration: 2000,
          panelClass: ['mat-toolbar', 'mat-warn']
        });
        console.error('Error fetching user data:', err);
      }
    });
  }
  

  updatePrice(size:string){

    if (size === this.previousSize) {
      return; // Do nothing if the same size is clicked again
  }

    let priceMultiplier = 1; // Default multiplier for size S
    const basePrice = this.productData.productPrice;

        switch(size) {
            case 'M':
                priceMultiplier = 1.3;
                break;
            case 'L':
                priceMultiplier = 1.5;
                break;
            case 'XL':
                priceMultiplier = 1.7;
                break;
            case 'XXL':
                priceMultiplier = 1.9;
                break;
    }
    this.proPrice = Math.round(basePrice * priceMultiplier);
    this.previousSize = size;
  }
}
