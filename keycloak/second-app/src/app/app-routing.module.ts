import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AppAuthGuard } from 'src/auth/app-auth-guard';
import { SomeComponentModule } from './some-component/some-component.module';
import { OtherComponentModule } from './other-component/other-component.module';
import { AppComponent } from './app.component';
import { NotAllowModule } from './not-allow-component/not-allow.module';

const routes: Routes = [
  {
    path: 'somecomponent', 
    loadChildren: () => SomeComponentModule,
    canActivate: [AppAuthGuard], 
    data: { roles: ['admin'] }
  },
  { 
    path: 'othercomponent', 
    loadChildren: () => OtherComponentModule,
    canActivate: [AppAuthGuard], 
    data: { roles: ['admin', 'basic'] }
  },
  { 
    path: 'notallow', 
    loadChildren: () => NotAllowModule,
    canActivate: [AppAuthGuard], 
    data: { roles: [] }
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule],
  providers: [AppAuthGuard]
})
export class AppRoutingModule { }
