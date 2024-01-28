import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class SupplierService {

  supplierUrl:string=`http://localhost:9000/api/v1/userService`

  constructor(private httpClient:HttpClient) { }

  requestForSupplier(userEmail:any){
    return this.httpClient.get(`${this.supplierUrl}/add/supplier/${userEmail}`)
  }

  getUserWaiting(){
    let httpHeader = new HttpHeaders({
      "Authorization":"Bearer "+localStorage.getItem("Token")
    })
    let reqOption = {headers:httpHeader}

    return this.httpClient.get(`${this.supplierUrl}/getSupplier/waiting`,reqOption);
  }

  getUserWaitingImg(userEmail:string){
    return this.httpClient.get(`${this.supplierUrl}/getSupplier/image/${userEmail}`);
  }

  approveAndDenyReq(email:string,supType:boolean){
    let httpHeader = new HttpHeaders({
      "Authorization":"Bearer "+localStorage.getItem("Token")
    })
    let reqOption = {headers:httpHeader}
    return this.httpClient.get(`${this.supplierUrl}/approveOrDeny?email=${email}&supType=${supType}`,reqOption)
  }

  getSupplierRole(){
    let httpHeader = new HttpHeaders({
      "Authorization":"Bearer "+localStorage.getItem("Token")
    })
    let reqOption = {headers:httpHeader}
    return this.httpClient.get(`${this.supplierUrl}/getUserRole`,reqOption)
  }
}
