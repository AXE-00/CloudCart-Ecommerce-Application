import { Component } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterOutlet} from '@angular/router';
import { NavBarComponent } from "./nav-bar/nav-bar.component";
import { FooterComponent } from './footer/footer.component';
import { MainFrontComponent } from "./main-front/main-front.component";
import { LoginComponent } from "./login/login.component";
import {HttpClientModule} from "@angular/common/http";
import { FormsModule,ReactiveFormsModule } from '@angular/forms';



@Component({
    selector: 'app-root',
    standalone: true,
    templateUrl: './app.component.html',
    styleUrl: './app.component.css',
    imports: [
               CommonModule,
               RouterOutlet,
               NavBarComponent, 
               FooterComponent, 
               MainFrontComponent,
               LoginComponent,
               HttpClientModule,FormsModule,
               ReactiveFormsModule
               ]
})
export class AppComponent {
  title = 'CloudCart';
}
