import { Component, OnInit } from '@angular/core';
import { DomSanitizer,SafeUrl } from '@angular/platform-browser';
import { UserService } from '../service/user.service';
import { RouterModule } from '@angular/router';
import { MatDialog } from '@angular/material/dialog';
import { UpdateUserComponent } from '../update-user/update-user.component';

@Component({
  selector: 'app-profile',
  standalone: true,
  imports: [RouterModule],
  templateUrl: './profile.component.html',
  styleUrl: './profile.component.css'
})
export class ProfileComponent implements OnInit{

  userImg:SafeUrl='';
  userName:any='';
  email:any='';
  phoneNo:any='';
  constructor(private userSer:UserService,private sanitizer:DomSanitizer,private dialog:MatDialog){}

  ngOnInit(): void {

    this.userName=localStorage.getItem('name');
    this.userSer.getUserData().subscribe((data:any)=>{
        if(data){
             console.log(data);
             this.email = data.userEmail;
             this.phoneNo = data.phoneNo;
        }
    })


    this.userSer.getUserImage().subscribe((data:any)=>{
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
    });

  }


  updateUser(){
    const dialogRef = this.dialog.open(UpdateUserComponent, {
      width:'auto',
    });
  }
}
