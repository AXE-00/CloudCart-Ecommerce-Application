import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

@Injectable({
  providedIn: 'root'
})
export class UserService {

  userUrl:string=`http://localhost:9000/api/v1/userService`;

  constructor(private httpClient:HttpClient) { }

  getUserName(){
    let httpHeader = new HttpHeaders({
      "Authorization":"Bearer "+localStorage.getItem('Token') 
    });
    let reqOption = {headers:httpHeader}
    console.log(reqOption);

    return this.httpClient.get(`${this.userUrl}/get/userName`,reqOption);
  }

  getUserImage(){
    let httpHeader = new HttpHeaders({
      "Authorization":"Bearer "+localStorage.getItem('Token') 
    });
    let reqOption = {headers:httpHeader}
    console.log(reqOption);

    return this.httpClient.get(`${this.userUrl}/get/userImage`,reqOption);
  }

  getUserData(){
    let httpHeader = new HttpHeaders({
      "Authorization":"Bearer "+localStorage.getItem('Token') 
    });
    let reqOption = {headers:httpHeader}
    console.log(reqOption);

    return this.httpClient.get(`${this.userUrl}/get/userData`,reqOption);
  }
  

  updateUser(formData:FormData){
    let httpHeader = new HttpHeaders({
      "Authorization":"Bearer "+localStorage.getItem('Token') 
    });
    let reqOption = {headers:httpHeader}
    console.log(reqOption);

    return this.httpClient.put(`${this.userUrl}/update/user`,formData,reqOption)
  }

}
