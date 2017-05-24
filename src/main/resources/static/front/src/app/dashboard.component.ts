import {Component, Inject, OnInit} from '@angular/core'
import {HeroService} from "./hero.service";
import {Hero} from "./Hero";

@Component({
  selector: 'my-dashboard',
  templateUrl: './dashboard.component.html'

})

export class DashboardComponent implements OnInit{
  heroes : Hero[]
  private heroServise: HeroService;
 constructor(@Inject(HeroService) heroServise : HeroService){
     this.heroServise = heroServise;
 }
 ngOnInit(){
   this.heroServise.getHeroes().then(heroes => this.heroes = heroes.slice(1,5));
 }


}
