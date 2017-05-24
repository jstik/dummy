import {Inject, Injectable} from '@angular/core';
import {Hero} from './Hero';
import  {Http, Headers} from '@angular/http';
import 'rxjs/add/operator/toPromise';

@Injectable()
export class HeroService {
  private heroesUrl : string = 'api/heroes';  // URL to web api
  http : Http;

  constructor(@Inject(Http) http : Http){
    this.http = http;

  }

  getHeroes(): Promise<Hero[]> {
    return this.http.get(this.heroesUrl).toPromise()
      .then( res => res.json().data  as Hero[])
      .catch(this.handleError);
  }
  private handleError(error: any): Promise<any> {
    console.error('An error occurred', error); // for demo purposes only
    return Promise.reject(error.message || error);
  }

  getHero(id: number) : Promise<Hero> {
    const url = `${this.heroesUrl}/${id}`;
    return this.http.get(url).toPromise().then( res => res.json.data as Hero)
      .catch(this.handleError);
  }
}
