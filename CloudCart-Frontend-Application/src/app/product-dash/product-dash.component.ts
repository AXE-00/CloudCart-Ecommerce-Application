import { Component, OnInit } from '@angular/core';
import { ProductService } from '../service/product.service';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';

@Component({
  selector: 'app-product-dash',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './product-dash.component.html',
  styleUrl: './product-dash.component.css'
})
export class ProductDashComponent implements OnInit {


  productsData:any=[];
  productsLoaded:boolean=false;

  constructor(private productService:ProductService, private router:Router){}

  ngOnInit(): void {
   this.fetchProducts();
  }

  fetchProducts(){
    this.productService.getProduct().subscribe({
      next:data=>{
       this.productsData = data;
       console.log(this.productsData);
       this.productsLoaded=true;
      }
    })
  }

  getProduct(productId:number){
   this.productService.productId=productId;
   console.log(this.productService.productId);
   this.router.navigateByUrl('/product');
  }
}
