import { Routes } from '@angular/router';
import { LoginComponent } from './login/login.component';
import { MainFrontComponent } from './main-front/main-front.component';
import { SignUpComponent } from './sign-up/sign-up.component';
import { ProfileComponent } from './profile/profile.component';
import { AddProductComponent } from './add-product/add-product.component';
import { authGuardGuard } from './guard/auth-guard.guard';
import { SupplierDashboardComponent } from './supplier-dashboard/supplier-dashboard.component';


export const routes: Routes = [
    {
       path:"",
       component:MainFrontComponent, 
       title:"CloudCart | Home"
    },{
      path:"login",
      component:LoginComponent,
      title:"Login"
    },
    {
        path:"signUp",
        component:SignUpComponent,
        title:"Sign Up"
    },
    {
        path:"profile",
        component:ProfileComponent,
        title:"User | Profile",
        // canActivate:[authGuardGuard]
    },
    {
        path:"addProduct",
        component:AddProductComponent,
        title:"Add | Product"
    },
    {
        path:"supplierDash",
        component:SupplierDashboardComponent,
        title:"Supplier | Dashboard"
    }
];
