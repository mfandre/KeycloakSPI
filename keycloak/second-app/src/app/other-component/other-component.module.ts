import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { OtherComponentRoutingModule } from './other-component-routing.module';
import { OtherComponentComponent } from './other-component.component';

@NgModule({
  imports: [
    CommonModule,
    OtherComponentRoutingModule
  ],
  declarations: [OtherComponentComponent]
})
export class OtherComponentModule { }