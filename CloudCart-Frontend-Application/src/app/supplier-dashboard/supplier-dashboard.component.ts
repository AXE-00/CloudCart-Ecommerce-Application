import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { UserService } from '../service/user.service';
import { MatSnackBar } from '@angular/material/snack-bar';

@Component({
  selector: 'app-supplier-dashboard',
  standalone: true,
  imports: [RouterModule,FormsModule,CommonModule],
  templateUrl: './supplier-dashboard.component.html',
  styleUrl: './supplier-dashboard.component.css'
})
export class SupplierDashboardComponent implements OnInit {

  userEmail:any='';
  otpClicked:boolean=true;
  verifyClicked:boolean=false;
  supplierVerified:boolean=false;
  timeLeft=60;
  otpGet:any=0;
  checkOtp:any=0;
  constructor(private userSer:UserService, private _matSnack:MatSnackBar){}

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
      }
    })

    var countDownInterval = setInterval(()=>{
    
      this.timeLeft--;
  
      if(this.timeLeft == 0){

        clearInterval(countDownInterval);
        this.otpClicked=true;
        this.verifyClicked=false;
        this.timeLeft=60;
      }
     },1000) 
  }

  verifyOtp(){
    if(this.otpGet==this.checkOtp){
      this._matSnack.open(`Verification Completed..`, 'success', {
        duration: 3000,
        panelClass: ['mat-toolbar', 'mat-primary']
      });
     
    }
    else{
      this._matSnack.open(`Entered OTP is Not Correct..`, 'Failure', {
        duration: 3000,
        panelClass: ['mat-toolbar', 'mat-primary']
      });
      this.timeLeft=60;
    }
  }
}
