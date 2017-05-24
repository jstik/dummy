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
var router_1 = require("@angular/router");
var common_1 = require("@angular/common");
var hero_service_1 = require("./hero.service");
require("rxjs/add/operator/switchMap");
var HeroDetailComponent = (function () {
    function HeroDetailComponent(heroService, activeRouter, location) {
        this.heroService = heroService;
        this.activeRouter = activeRouter;
        this.location = location;
    }
    HeroDetailComponent.prototype.ngOnInit = function () {
        var _this = this;
        this.activeRouter.params.switchMap(function (params) { return _this.heroService.getHero(+params['id']); }).subscribe(function (hero) { return _this.hero = hero; });
    };
    HeroDetailComponent.prototype.goBack = function () {
        this.location.back();
    };
    return HeroDetailComponent;
}());
__decorate([
    core_1.Input()
], HeroDetailComponent.prototype, "hero", void 0);
HeroDetailComponent = __decorate([
    core_1.Component({
        selector: "hero-detail",
        templateUrl: './hero.details.html'
    }),
    __param(0, core_1.Inject(hero_service_1.HeroService)),
    __param(1, core_1.Inject(router_1.ActivatedRoute)),
    __param(2, core_1.Inject(common_1.Location))
], HeroDetailComponent);
exports.HeroDetailComponent = HeroDetailComponent;
