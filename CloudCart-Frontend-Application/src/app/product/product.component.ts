import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common'; 
import { ProductService } from '../service/product.service';

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

  constructor(private proSer:ProductService){}

  ngOnInit(): void {
    this.proId=this.proSer.productId;
    console.log(this.proId);
    
    this.proSer.getProductById(this.proId).subscribe({
      next:data=>{
        this.productData=data;
        this.proCategory=this.productData.category;
        console.log(this.proCategory);
        
        console.log(this.productData);
        this.getProductByCat();
      }
    })

    
  }
  getStarsArray(): number[] {
    const rating = this.productData.productRating || 0;
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
}
