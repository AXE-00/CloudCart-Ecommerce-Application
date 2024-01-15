import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AbstractControl, FormBuilder, Validators } from '@angular/forms';
import { FormsModule,ReactiveFormsModule } from '@angular/forms';
import { LoginService } from '../service/login.service';

@Component({
  selector: 'app-login',
  standalone: true,
  imports:[FormsModule,ReactiveFormsModule,CommonModule],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css'
})
export class LoginComponent {

  email:string="";

constructor(private fb:FormBuilder){}

formData=this.fb.group({
  email : ["",[Validators.required,this.checkEmail]],
  password : ["",[Validators.required,Validators.minLength(7)]]
}) 

 getEmail(){
  return this.formData.get('email');
 }

 getPassword(){
  return this.formData.get('password');
 }

 checkEmail(data:AbstractControl){
  if(data.value !==''){
    const emailVal = data.value;
    const emailString = emailVal.split(',').map((e:string)=>e.trim());
    const regEx = /^(([^<>()[\]\.,;:\s@\"]+(\.[^<>()[\]\.,;:\s@\"]+)*)|(\".+\"))@(gmail\.com|yahoo\.com)$/i;
    const emailInvalid = emailString.every((e:string)=>e.match(regEx)!==null);
    if(!emailInvalid){
      return {checkEmail:false}
    }
  }
  return null;
 }

 getData(){
  const formInfo = this.formData.value;
  console.log(formInfo);
 }
 
}
