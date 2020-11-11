import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { SomeComponentRoutingModule } from './some-component-routing.module';
import { SomeComponentComponent } from './some-component.component';

@NgModule({
  imports: [
    CommonModule,
    SomeComponentRoutingModule
  ],
  declarations: [SomeComponentComponent]
})
export class SomeComponentModule { }