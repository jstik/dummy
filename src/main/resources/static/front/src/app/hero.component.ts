import { Component, OnInit, Inject } from '@angular/core';

import {HeroService} from "./hero.service"
import {Hero} from "./Hero";

import {Router} from '@angular/router'


@Component({
  selector: 'my-heroes',
  template: `<hero-form></hero-form> <br>
            <h2>Heroes List</h2>
            <ul class="heroes">
             <li *ngFor="let hero of heroes"  [class.selected] = "hero === selectedHero" (click)="onSelect(hero)">
                <span class="badge" > {{hero.id}} </span> {{hero.name}}
             </li>
            </ul>
  <div *ngIf="selectedHero">
    <h2>
      {{selectedHero.name | uppercase}} is my hero
    </h2>
    <button (click)="gotoDetail()">View Details</button>
  </div>
           `,
   styleUrls : ["./hero.component.css"]
})

export class HeroComponent  implements OnInit{
  router : Router;

  constructor( @Inject(HeroService) private heroService : HeroService,
               @Inject(Router)  router : Router) {
    this.router = router;
  }

  heroes: Hero[];
  selectedHero : Hero;

  onSelect(hero : Hero) : void {
    this.selectedHero = hero;
  }
  getHeroes(): void {
    this.heroService.getHeroes().then(heroes => this.heroes = heroes );
  }
  ngOnInit(): void {
    this.getHeroes();
  }
  gotoDetail() {
    return this.router.navigate(['detail', this.selectedHero.id]);
  }

}
