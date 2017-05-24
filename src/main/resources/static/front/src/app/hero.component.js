"use strict";
var __decorate = (this && this.__decorate) || function (decorators, target, key, desc) {
    var c = arguments.length, r = c < 3 ? target : desc === null ? desc = Object.getOwnPropertyDescriptor(target, key) : desc, d;
    if (typeof Reflect === "object" && typeof Reflect.decorate === "function") r = Reflect.decorate(decorators, target, key, desc);
    else for (var i = decorators.length - 1; i >= 0; i--) if (d = decorators[i]) r = (c < 3 ? d(r) : c > 3 ? d(target, key, r) : d(target, key)) || r;
    return c > 3 && r && Object.defineProperty(target, key, r), r;
};
var __param = (this && this.__param) || function (paramIndex, decorator) {
    return function (target, key) { decorator(target, key, paramIndex); }
};
Object.defineProperty(exports, "__esModule", { value: true });
var core_1 = require("@angular/core");
var hero_service_1 = require("./hero.service");
var router_1 = require("@angular/router");
var HeroComponent = (function () {
    function HeroComponent(heroService, router) {
        this.heroService = heroService;
        this.router = router;
    }
    HeroComponent.prototype.onSelect = function (hero) {
        this.selectedHero = hero;
    };
    HeroComponent.prototype.getHeroes = function () {
        var _this = this;
        this.heroService.getHeroes().then(function (heroes) { return _this.heroes = heroes; });
    };
    HeroComponent.prototype.ngOnInit = function () {
        this.getHeroes();
    };
    HeroComponent.prototype.gotoDetail = function () {
        return this.router.navigate(['detail', this.selectedHero.id]);
    };
    return HeroComponent;
}());
HeroComponent = __decorate([
    core_1.Component({
        selector: 'my-heroes',
        template: "<hero-form></hero-form> <br>\n            <h2>Heroes List</h2>\n            <ul class=\"heroes\">\n             <li *ngFor=\"let hero of heroes\"  [class.selected] = \"hero === selectedHero\" (click)=\"onSelect(hero)\">\n                <span class=\"badge\" > {{hero.id}} </span> {{hero.name}}\n             </li>\n            </ul>\n  <div *ngIf=\"selectedHero\">\n    <h2>\n      {{selectedHero.name | uppercase}} is my hero\n    </h2>\n    <button (click)=\"gotoDetail()\">View Details</button>\n  </div>\n           ",
        styleUrls: ["./hero.component.css"]
    }),
    __param(0, core_1.Inject(hero_service_1.HeroService)),
    __param(1, core_1.Inject(router_1.Router))
], HeroComponent);
exports.HeroComponent = HeroComponent;
