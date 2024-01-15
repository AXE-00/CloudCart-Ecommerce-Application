import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormsModule,ReactiveFormsModule, Validators } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { AbstractControl } from '@angular/forms';

@Component({
  selector: 'app-sign-up',
  standalone: true,
  imports: [ReactiveFormsModule,CommonModule,FormsModule,RouterModule],
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.css']
})
export class SignUpComponent {

  fileName="No file choosen";
  
  constructor(private fb:FormBuilder){}

  formData=this.fb.group({
    uName:["",Validators.required],
    email:["",[Validators.required,this.checkEmail]],
    password:["",Validators.required],
    cPassword:["",[Validators.required,this.passwordMatch]],
    phoneNum:["",[Validators.required,Validators.pattern(/^[789]\d{9,9}$/)]]
  })

  getUName(){
    return this.formData.get('uName');
  }

  getEmail(){
    return this.formData.get('email');
  }

  getPassword(){
    return this.formData.get('password');
  }

  getcPassword(){
    return this.formData.get('cPassword');
  }

  onImageUpload(event:any){
      this.fileName=event.target.files[0].name;
      console.log(this.fileName);
  }

  checkEmail(val:AbstractControl){
    if(val.value !==''){
      const emailVal = val.value;
      const emailString = emailVal.split(',').map((e:string)=>e.trim());
      const regEx = /^(([^<>()[\]\.,;:\s@\"]+(\.[^<>()[\]\.,;:\s@\"]+)*)|(\".+\"))@(gmail\.com|yahoo\.com)$/i;
      const invalidEmail = emailString.every((e:string)=>e.match(regEx)!==null);
      if(!invalidEmail){
        return {checkEmail:false}
      }
    }
     return null; 
  }

  passwordMatch(data:AbstractControl){
    const password = data.get('password')?.value;
    const cPass  = data.get('cPassword')?.value;
    if(!password || !cPass){
      return null
    }
    if(password != cPass){
      return {mustMatch:false}
    }
    return null;
  }
}
