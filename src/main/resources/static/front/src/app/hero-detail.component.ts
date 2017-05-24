import {Component, Inject, Input, OnInit} from '@angular/core'
import {Hero} from "./Hero";
import {ActivatedRoute, Params} from "@angular/router"
import {Location} from '@angular/common'
import  {HeroService} from "./hero.service";
import 'rxjs/add/operator/switchMap';
@Component({
  selector:"hero-detail",
  templateUrl:'./hero.details.html'
})

export class HeroDetailComponent implements OnInit{
  @Input() hero : Hero;
  activeRouter : ActivatedRoute;
  heroService : HeroService;
  location : Location;
  constructor(@Inject(HeroService) heroService : HeroService,
              @Inject(ActivatedRoute) activeRouter: ActivatedRoute,
              @Inject(Location) location : Location ){
    this.heroService = heroService;
    this.activeRouter = activeRouter;
    this.location = location;

  }
  ngOnInit(): void {
    this.activeRouter.params.switchMap(
      (params : Params) => { return this.heroService.getHero(+params['id']) }
      ).subscribe(hero => this.hero = hero);
  }

  goBack(){
    this.location.back();
  }

}
