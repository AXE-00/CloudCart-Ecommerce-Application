import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class CartService {

  cartUrl:string="http://localhost:9000/v1/api/cart"

  constructor(private httpClient:HttpClient) { }

  addProductInCart(cartRequest:any){
    let httpHeader = new HttpHeaders({
      "Authorization":"Bearer "+localStorage.getItem('Token')
    });
    let reqOption = {headers:httpHeader}
    console.log(reqOption)
    console.log(cartRequest)
    return this.httpClient.post(`${this.cartUrl}/addProduct`,cartRequest,reqOption)
  }

}
