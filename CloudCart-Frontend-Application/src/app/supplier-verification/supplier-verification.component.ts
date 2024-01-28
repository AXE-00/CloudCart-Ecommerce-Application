import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { UserService } from '../service/user.service';
import { MatSnackBar } from '@angular/material/snack-bar';
import { FormsModule } from '@angular/forms';
import { Router } from '@angular/router';
import { SupplierService } from '../service/supplier.service';

@Component({
  selector: 'app-supplier-verification',
  standalone: true,
  imports: [FormsModule,CommonModule],
  templateUrl: './supplier-verification.component.html',
  styleUrl: './supplier-verification.component.css'
})
export class SupplierVerificationComponent implements OnInit{

  userEmail:any='';
  otpClicked:boolean=true;
  verifyClicked:boolean=false;
  countDownInterval: any;
  timeLeft=60;
  otpGet:any;
  checkOtp:any=0;

  constructor(private userSer:UserService,private _matSnack:MatSnackBar,private route:Router, private supplierSer:SupplierService){}

  ngOnInit(): void {
    this.userSer.getUserData().subscribe((data:any)=>{
      this.userEmail=data.userEmail;
    })
  }

  getOtp(){
    this.userSer.getOtp(this.userEmail).subscribe({
      next:data=>{
        console.log(data);
        this.checkOtp=data;
        
        this._matSnack.open(`OTP Sent Successfully..`, 'success', {
          duration: 2000,
          panelClass: ['mat-toolbar', 'mat-primary']
        });
        
        this.otpClicked=false;
        this.verifyClicked=true;
      },
      error:err=>{
        this._matSnack.open(`Error is occurring ..`, 'Failure', {
          duration: 2000,
          panelClass: ['mat-toolbar', 'mat-primary']
        });
      }
    })

    this.countDownInterval = setInterval(()=>{
    
      this.timeLeft--;
  
      if(this.timeLeft === 0){

        clearInterval(this.countDownInterval);
        this.otpClicked=true;
        this.verifyClicked=false;
      
      }
     },1000) 
  }

  verifyOtp(){
    if(this.otpGet==this.checkOtp){
     
      this.supplierSer.requestForSupplier(this.userEmail).subscribe({
      next:data=>{
        this._matSnack.open(`Request have been Sent..`, 'success', {
          duration: 3000,
          panelClass: ['mat-toolbar', 'mat-primary']
        });
       }
      })
      this.stopCountDown(); 
      this.route.navigateByUrl("/");
    }
    else{
      this._matSnack.open(`Entered OTP is Not Correct..`, 'Failure', {
        duration: 3000,
        panelClass: ['mat-toolbar', 'mat-primary']
      });
      this.timeLeft=60;
    }
  }

  stopCountDown(){
    if(this.countDownInterval){
      clearInterval(this.countDownInterval);
    }
  }

}
