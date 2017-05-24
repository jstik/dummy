import { platformBrowserDynamic } from '@angular/platform-browser-dynamic';
import {enableDebugTools} from '@angular/platform-browser';

import { AppModule } from './app/app.module';

platformBrowserDynamic().bootstrapModule(AppModule);
