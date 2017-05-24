'use strict';
import brandTemplate from './brandTemplate.html';
import Helper from '../common/helper/';
import Dropdown from '../common/dropdown';

function createNavigator(options) {
  return brandToHtml({name: ''});
}

function brandToHtml(data) {
  return brandTemplate(data);
}

class HTMLComponent{
  constructor(name, options = {}){
    const self = this;
    self.$context = $(options.context);
    self.name =  name;
    self.options = options;
    self.$dom = new Object(null);
  }

  render(callbacks){
    const $self = this;
    $self.$context.addClass('page-' + Helper.camelCase2Dash(self.name));
    $(function () {
      if( typeof callbacks === 'function'){
         callbacks = {main : callbacks}
      }
      const main = callbacks;
      $self.$context.append(callbacks.main());
    });
    return $self;
  }

}
document.addEventListener("DOMContentLoaded", ()=>{
  new HTMLComponent("brand", {context: ".container-fluid"})
    .render(createNavigator);
});
document.addEventListener("DOMContentLoaded", ()=>{

  Dropdown.navDropdown('.navbar-right').renderDropdown();
});
