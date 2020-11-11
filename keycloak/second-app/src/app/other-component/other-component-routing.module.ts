import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { OtherComponentComponent } from './other-component.component';


const routes: Routes = [
  {
    path: '',
    component: OtherComponentComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class OtherComponentRoutingModule { }