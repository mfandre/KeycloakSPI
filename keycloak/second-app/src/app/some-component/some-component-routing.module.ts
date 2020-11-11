import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { SomeComponentComponent } from './some-component.component';


const routes: Routes = [
  {
    path: '',
    component: SomeComponentComponent
  }
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class SomeComponentRoutingModule { }