import { Routes } from '@angular/router';
import { NavBarComponent } from './nav-bar/nav-bar.component';
import { LoginComponent } from './login/login.component';
import { MainFrontComponent } from './main-front/main-front.component';


export const routes: Routes = [
    {
       path:"",
       component:MainFrontComponent 
    },{
      path:"login",
      component:LoginComponent,
      title:"Login"
    }
];
