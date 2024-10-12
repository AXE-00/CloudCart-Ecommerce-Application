import { Component, OnInit } from '@angular/core';
import { ProductService } from '../service/product.service';
import { CommonModule } from '@angular/common';
import { ActivatedRoute, Router } from '@angular/router';

@Component({
  selector: 'app-product-by-cat',
  standalone: true,
  imports: [CommonModule],
  templateUrl: './product-by-cat.component.html',
  styleUrl: './product-by-cat.component.css'
})
export class ProductByCatComponent implements OnInit{

  productsData:any=[];
  productsLoaded:boolean=false;
  category:string='women';
  productId?:number;

  constructor(private productService:ProductService, private route:ActivatedRoute,private router:Router ){}

  ngOnInit(): void {
    this.route.params.subscribe(params => {
      this.category = params['category'];
      this.fetchProducts();
    })
    
  }

  fetchProducts(){
    this.productService.getProductByCategory(this.category).subscribe({
      next:data=>{
       this.productsData = data;
       console.log(this.productsData);
       this.productsLoaded=true;
      }
    })
  }

  getProduct(proId:number){
     this.productId=proId;
     console.log(this.productId);
     this.productService.productId=proId;
     this.router.navigateByUrl("/product");
  }
}
