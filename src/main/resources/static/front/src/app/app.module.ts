import {NgModule}      from '@angular/core';
import {BrowserModule} from '@angular/platform-browser';
import {FormsModule}   from '@angular/forms';
import {HttpModule} from '@angular/http'

import {HeroDetailComponent} from './hero-detail.component';
import {HeroFormComponent} from './form-hero.component';
import {HeroComponent}  from './hero.component';
import {AppComponent} from './app.component';
import {HeroService} from './hero.service';
import  {DashboardComponent} from './dashboard.component';
import  {routing } from './app-routing.module';
import { InMemoryWebApiModule } from 'angular-in-memory-web-api';

import { InMemoryDataService }  from './in-memory-data.service';


@NgModule({
  imports: [
    BrowserModule,
    FormsModule,
    routing,
    HttpModule,
    InMemoryWebApiModule.forRoot(InMemoryDataService)
  ],
  declarations: [AppComponent, HeroComponent, HeroFormComponent, HeroDetailComponent, DashboardComponent],
  bootstrap: [AppComponent],
  providers: [HeroService],
})
export class AppModule {
}
