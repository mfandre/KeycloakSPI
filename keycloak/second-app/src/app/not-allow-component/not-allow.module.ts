import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { NotAllowRoutingModule } from './not-allow-routing.module';
import { NotAllowComponent } from './not-allow.component';

@NgModule({
  imports: [
    CommonModule,
    NotAllowRoutingModule
  ],
  declarations: [NotAllowComponent]
})
export class NotAllowModule { }