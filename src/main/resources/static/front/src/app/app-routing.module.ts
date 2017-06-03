import { NgModule }             from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { DashboardComponent }   from './dashboard.component';
import { HeroComponent }      from './hero.component';
import { HeroDetailComponent }  from './hero-detail.component';

const router : Routes=[
  {
    path: 'heroes',
    component: HeroComponent
  },
  {
    path : 'dashboard',
    component : DashboardComponent
  },
  {
    path :'detail/:id',
    component : HeroDetailComponent
  },
  {
    path :'',
    redirectTo : '/dashboard',
    pathMatch : 'full'
  },

];
@NgModule({
  imports: [ RouterModule.forRoot(router) ],
  exports: [ RouterModule ]
})

export const routing = RouterModule.forRoot(router);
//export class AppRoutingModule {}
