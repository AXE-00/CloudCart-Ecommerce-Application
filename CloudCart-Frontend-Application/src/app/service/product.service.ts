import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class ProductService {

  productUrl:string='http://localhost:9000/api/v1/productService';

  constructor(private httpClient:HttpClient) { }



  addProduct(formData:any){
    let httpHeader = new HttpHeaders({
      "Authorization":"Bearer "+localStorage.getItem('Token')
    });
    let reqOption = {headers:httpHeader}
    console.log(reqOption);
    
    return this.httpClient.post(`${this.productUrl}/addNewProduct`,formData,reqOption)
  }

  getAllProduct(){

    return this.httpClient.get(`${this.productUrl}/getAllProducts`)

  }
    
}
