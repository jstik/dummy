"use strict";
Object.defineProperty(exports, "__esModule", { value: true });
var router_1 = require("@angular/router");
var dashboard_component_1 = require("./dashboard.component");
var hero_component_1 = require("./hero.component");
var hero_detail_component_1 = require("./hero-detail.component");
var router = [
    {
        path: 'heroes',
        component: hero_component_1.HeroComponent
    },
    {
        path: 'dashboard',
        component: dashboard_component_1.DashboardComponent
    },
    {
        path: 'detail/:id',
        component: hero_detail_component_1.HeroDetailComponent
    },
    {
        path: '',
        redirectTo: '/dashboard',
        pathMatch: 'full'
    },
];
exports.routing = router_1.RouterModule.forRoot(router);
//export class AppRoutingModule {}
