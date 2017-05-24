import {Component} from "@angular/core";
import {Hero} from "./Hero"
declare var  require:any;
var foo = require('./js/build/twst.js');
@Component({
  selector: 'hero-form',
  templateUrl: './templates/hero-form.component.html'
})
export class HeroFormComponent {
  powers: Array<string> =['Really Smart', 'Super Flexible','Super Hot', 'Weather Changer'];
  model = new Hero(18, 'Dr IQ', this.powers[0], 'Chuck Overstreet');
  submitted: boolean = false;

  onSubmit() {
    this.submitted = true;
  }

  newHero() {
    this.model = new Hero(42, foo.createHeroName(), '');
  }

  get diagnostic() {
    return JSON.stringify(this.model);
  }


}
