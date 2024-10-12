import { Component, OnInit} from '@angular/core';
import { CommonModule } from '@angular/common';
import { Router, RouterModule } from '@angular/router';
import { LoginService } from '../service/login.service';
import { UserService } from '../service/user.service';
import { SupplierService } from '../service/supplier.service';

@Component({
  selector: 'app-nav-bar',
  standalone: true,
  imports: [RouterModule, CommonModule],
  templateUrl: './nav-bar.component.html',
  styleUrl: './nav-bar.component.css'
})
export class NavBarComponent implements OnInit{
  
userName:any;
supplierRole:any;
loginTime:any="Login";
supplierValid:boolean=false;
supplierVer:boolean=true;
role=true;
loggedIn:boolean=false;
fav:string='';

constructor(private logService:LoginService,private userSer:UserService,private supService:SupplierService, private router :Router){}
  ngOnInit(): void {
    
    this.userName ="User" ;
    

    this.logService.userLoggedIn.subscribe(()=>{

      this.userName = localStorage.getItem('name');
        this.loginTime="LogOut";

        console.log(this.supplierRole=='supplier');

        this.supService.getSupplierRole().subscribe({
          next:(data:any)=>{
            console.log(data);
            this.supplierRole = data.suppRole;
            localStorage.setItem('supRole',this.supplierRole)
            if(this.supplierRole=='supplier'){
        
              this.supplierValid=true;
              this.supplierVer=false;
            }  
          } 
        })
       

      if(localStorage.getItem('role')==='adminRole'){
        this.role=false;
        this.supplierVer=false;
      }

    if(this.logService.isLoggedIn){
      this.loggedIn=true;
      this.fav="Favourite";
    }
    })
    
  }

  clicked(){
    if(this.loginTime==='Login'){
      this.router.navigateByUrl('/login');
    }
    if(this.loginTime === 'LogOut'){
      this.role=true;
      this.supplierVer=true;
      this.loggedIn=false;
      this.logService.loginfailure();
      localStorage.clear();
      this.router.navigateByUrl("/login")
      this.loginTime='Login';
      this.userName ="User" ;
    }
  }

  toggleMenu() {
    var menu = document.getElementById("expandableMenu");
    menu?.classList.toggle("active");
}
}
