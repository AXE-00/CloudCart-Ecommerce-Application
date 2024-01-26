import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterOutlet} from '@angular/router';
import { NavBarComponent } from "./nav-bar/nav-bar.component";
import { FooterComponent } from './footer/footer.component';
import { MainFrontComponent } from "./main-front/main-front.component";
import { LoginComponent } from "./login/login.component";
import {HttpClientModule} from "@angular/common/http";
import { SignUpComponent } from './sign-up/sign-up.component';
import { FormsModule,ReactiveFormsModule } from '@angular/forms';
import { AddProductComponent } from "./add-product/add-product.component";
import { SupplierDashboardComponent } from "./supplier-dashboard/supplier-dashboard.component";



@Component({
    selector: 'app-root',
    standalone: true,
    templateUrl: './app.component.html',
    styleUrl: './app.component.css',
    imports: [
        CommonModule,
        RouterOutlet,
        NavBarComponent,
        SignUpComponent,
        FooterComponent,
        MainFrontComponent,
        LoginComponent,
        HttpClientModule, FormsModule,
        ReactiveFormsModule,
        AddProductComponent,
        SupplierDashboardComponent
    ]
})
export class AppComponent {
  title = 'CloudCart';
}
