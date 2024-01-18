import {EventEmitter, Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";

@Injectable({
	providedIn: 'root'
})
export class LoginService {

	isLoggedIn:boolean=false;

	userLoggedIn = new EventEmitter<any>();

	authUrl: String = "http://localhost:9000/api/v1/authService";

	constructor(private httpClient: HttpClient) {
	}

	loginSuccess(){
		this.isLoggedIn=true;
		this.userLoggedIn.emit(true);
	}

	loginfailure(){
		this.isLoggedIn=false;
	}

	getLoginStatus(){
        return this.isLoggedIn;
	}

	login(loginData: any) {
    // console.log(loginData);
		return this.httpClient.post(`${this.authUrl}/user/login`, loginData);
	}
}
