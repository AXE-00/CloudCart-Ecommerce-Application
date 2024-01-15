import { Routes } from '@angular/router';
import { NavBarComponent } from './nav-bar/nav-bar.component';
import { LoginComponent } from './login/login.component';
import { MainFrontComponent } from './main-front/main-front.component';
import { SignUpComponent } from './sign-up/sign-up.component';


export const routes: Routes = [
    {
       path:"",
       component:MainFrontComponent 
    },{
      path:"login",
      component:LoginComponent,
      title:"Login"
    },
    {
        path:"signUp",
        component:SignUpComponent,
        title:"Sign Up"
    }
];
