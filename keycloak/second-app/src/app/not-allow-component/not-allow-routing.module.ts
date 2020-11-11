import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { NotAllowComponent } from './not-allow.component';


const routes: Routes = [
  {
    path: '',
    component: NotAllowComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class NotAllowRoutingModule { }