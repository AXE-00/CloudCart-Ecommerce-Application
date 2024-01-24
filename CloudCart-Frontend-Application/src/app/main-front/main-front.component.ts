import { Component } from '@angular/core';
import { NavBarComponent } from "../nav-bar/nav-bar.component";
import { ProductDashComponent } from "../product-dash/product-dash.component";

@Component({
    selector: 'app-main-front',
    standalone: true,
    templateUrl: './main-front.component.html',
    styleUrl: './main-front.component.css',
    imports: [ProductDashComponent]
})
export class MainFrontComponent {

}
