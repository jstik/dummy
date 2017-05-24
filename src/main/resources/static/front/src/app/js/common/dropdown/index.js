import Helper from '../helper/';
import dropdownTemplate from './template/dropdown.html';
import itemTemplate from './template/item.html';
import menuTemplate from './template/menu.html';
import separatorTemplate from './template/separator.html';
import navToggleTemplate from './template/navToggle.html';

var tenantURL = '/v1/tenant/list';

function Item(id, name, template, action) {
  this.id = id;
  this.name = name;
  this.template = template;
  this.action = action;
}
Item.prototype.render = function () {
  let data = {
    content: this.name,
    id : this.id
  };
  let itemTemplate = this.template(data);
  $(() => $('#' + this.id).on('click', this.action));
  return itemTemplate;
};
class Dropdown {
  constructor(name, options){
    const self= this;
    self.name = name;
    self.toggleTemplate = options.toggleTemplate;
    self.$context = $(options.context); // element where  dropdown should be injected
    self.items = options.items;
    self.options = options;
    return self;
  }
  renderMenu(items){
    const self = this;
   let itemsStr = '';
    items.forEach((item) => {
      itemsStr += item.render();
    } );
    let data ={};
    data.id = Helper.camelCase2Dash(self.name) + '-menu';
    data.class = Helper.camelCase2Dash(self.name) + '-menu-class';
    data.content = itemsStr;
    return menuTemplate(data);
  }

  renderToggle(template, data){
    return template(data);
  }

  renderDropdown(){
    const self = this;
    var toggleTemplate = self.toggleTemplate;
    let data ={};
    data.toggle = this.renderToggle(toggleTemplate, {}) ;
    data.menu = this.renderMenu(self.items);
    self.$context.append(dropdownTemplate(data));
    return self;
  }

}
export default {
  dropdown : Dropdown,
  navDropdown : function (context) {
    let options = {
      context : context,
      toggleTemplate : navToggleTemplate
    };
    let items = [];
    items.push(new Item("nav-user-item", "Users", itemTemplate, () => console.log('Users clicked')),
      new Item("nav-separator", 'separator', separatorTemplate),
      new Item("nav-tenant-item", 'Tenants', itemTemplate, () => {
        $.get(tenantURL).then( function (data) {
          console.log(data);
        }, function () {
          console.log("fail");
        } );
      })
    );
    options.items = items;
    return new Dropdown('navDropdown', options);
  }
};
