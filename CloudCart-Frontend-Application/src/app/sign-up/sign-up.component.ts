import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormBuilder, FormsModule,ReactiveFormsModule, Validators } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { AbstractControl } from '@angular/forms';
import { RegiService } from '../service/regi.service';
import {MatSnackBar} from '@angular/material/snack-bar';

@Component({
  selector: 'app-sign-up',
  standalone: true,
  imports: [ReactiveFormsModule,CommonModule,FormsModule,RouterModule],
  templateUrl: './sign-up.component.html',
  styleUrl: './sign-up.component.css'
})
export class SignUpComponent {

  fileName="No file choosen";
  uploadedImg:any = File;
  
  constructor(private fb:FormBuilder,private regiService:RegiService,private _matSnack:MatSnackBar){}

  formData=this.fb.group({
    userName:["",Validators.required],
    userEmail:["",[Validators.required,this.checkEmail]],
    password:["",Validators.required],
    cPassword:["",[Validators.required,this.passwordMatch]],
    phoneNo:["",[Validators.required,Validators.pattern(/^[789]\d{9,9}$/)]]
  })

  getUName(){
    return this.formData.get('userName');
  }

  getEmail(){
    return this.formData.get('userEmail');
  }

  getPassword(){
    return this.formData.get('password');
  }

  getcPassword(){
    return this.formData.get('cPassword');
  }

  onImageUpload(event:any){
      const imgData = event.target.files[0];
      this.fileName= imgData.name;
      this.uploadedImg=imgData;

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

  addUser(){
    const userData = this.formData.value;
    const fd = new FormData();

    fd.append('userData',JSON.stringify(userData))
    fd.append('file',this.uploadedImg)

    this.regiService.registerUser(fd).subscribe({
      next:data=>{
        console.log("Success",data);
        this._matSnack.open("User Added Successfully..!");
      }
    })
  }

}
