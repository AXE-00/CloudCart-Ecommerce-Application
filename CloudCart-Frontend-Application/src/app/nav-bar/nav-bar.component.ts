import { Component, OnInit} from '@angular/core';
import { Router, RouterModule } from '@angular/router';
import { LoginService } from '../service/login.service';
import { UserService } from '../service/user.service';

@Component({
  selector: 'app-nav-bar',
  standalone: true,
  imports: [RouterModule],
  templateUrl: './nav-bar.component.html',
  styleUrl: './nav-bar.component.css'
})
export class NavBarComponent implements OnInit{
  
userName:any;
loginTime:any="Login";

constructor(private logService:LoginService,private userSer:UserService, private router :Router){}
  ngOnInit(): void {
    
    this.userName ="User" ;

    this.logService.userLoggedIn.subscribe(()=>{

      this.userName = localStorage.getItem('name');
        this.loginTime="LogOut";

    })
  }

  clicked(){
    if(this.loginTime==='Login'){
      this.router.navigateByUrl('/login');
    }
    if(this.loginTime === 'LogOut'){
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
