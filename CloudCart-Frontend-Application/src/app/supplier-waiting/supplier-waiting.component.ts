import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { AdminDashboardComponent } from "../admin-dashboard/admin-dashboard.component";
import { SupplierService } from '../service/supplier.service';
import { DomSanitizer, SafeUrl } from '@angular/platform-browser';
import { MatSnackBar } from '@angular/material/snack-bar';
import { Router } from '@angular/router';

@Component({
    selector: 'app-supplier-waiting',
    standalone: true,
    templateUrl: './supplier-waiting.component.html',
    styleUrl: './supplier-waiting.component.css',
    imports: [AdminDashboardComponent,CommonModule]
})
export class SupplierWaitingComponent implements OnInit {

    supplierWaiting:any;
    listLoaded:boolean=false;
    dataPresent?:boolean;
    userImg: SafeUrl = '';

    constructor( 
        private supplierSer:SupplierService, 
        private sanitizer:DomSanitizer, 
        private _matSnack:MatSnackBar,
        private router:Router
        ){}

    ngOnInit(): void {
        this.getWaitingList();
    }


    reqApproved(email:string){
       this.supplierSer.approveAndDenyReq(email,true).subscribe({
        next:data=>{
            this._matSnack.open('User Request Approved..', 'success', {
                duration: 3000,
                panelClass: ['mat-toolbar', 'mat-primary']
              });
              
              this.ngOnInit();
              
            this.router.navigateByUrl('/supplierWait');
        }
       })
    }
    

    deniedReq(email:string){
        this.supplierSer.approveAndDenyReq(email,false).subscribe({
            next:data=>{
                this._matSnack.open('User Request Denied..', 'success', {
                    duration: 3000,
                    panelClass: ['mat-toolbar', 'mat-primary']
                  });
            }
           })
    }

    getWaitingList(){
        this.supplierSer.getUserWaiting().subscribe((data:any)=>{
            if(data!=null && data.length>0){
                this.dataPresent=true;
                this.supplierWaiting=data; 
            this.supplierWaiting.map((supplier:any)=>supplier.userEmail).forEach((e:string)=>{
                this.supplierSer.getUserWaitingImg(e).subscribe((data:any)=>{
                    if(data && data.imageData){
                        const imageData = data.imageData;
                        const binaryData = atob(imageData)
                        const arrayBuffer = new ArrayBuffer(binaryData.length); // creates a arrayBuffer of equal Length of binaryData
                        const uint8Array = new Uint8Array(arrayBuffer);
                        for (let i = 0; i < binaryData.length; i++) {
                          uint8Array[i] = binaryData.charCodeAt(i);
                        }
                        const blob = new Blob([uint8Array], { type: 'image/png' });
                        this.userImg = this.sanitizer.bypassSecurityTrustUrl(URL.createObjectURL(blob));
                      }
                })
            });
            console.log(this.supplierWaiting);
            
            this.listLoaded=true; 
            }
             else{
                this.listLoaded=true; 
                this.dataPresent=false;
                   const board = document.getElementById('board');
                   if(board){
                    board.style.height="20vw";
                   }
                   const element = document.createElement('p');
                   element.innerText="No Request For Approval";
                   element.style.alignItems="center";
                   board?.appendChild(element);
             } 
           }
        )
    }
}
