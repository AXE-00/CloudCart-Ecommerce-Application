import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";

@Injectable({
	providedIn: 'root'
})
export class LoginService {

	authUrl: String = "http://localhost:9000/api/v1/authService"

	constructor(private httpClient: HttpClient) {
	}

	login(loginData: any) {
    console.log(loginData);
		return this.httpClient.post(`${this.authUrl}/login`, loginData);
	}
}
