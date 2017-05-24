"use strict";
var _1 = require("../helper/");
var dropdown_html_1 = require("./template/dropdown.html");
var item_html_1 = require("./template/item.html");
var menu_html_1 = require("./template/menu.html");
var separator_html_1 = require("./template/separator.html");
var navToggle_html_1 = require("./template/navToggle.html");
var tenantURL = '/v1/tenant/list';
function Item(id, name, template, action) {
    this.id = id;
    this.name = name;
    this.template = template;
    this.action = action;
}
Item.prototype.render = function () {
    var _this = this;
    var data = {
        content: this.name,
        id: this.id
    };
    var itemTemplate = this.template(data);
    $(function () { return $('#' + _this.id).on('click', _this.action); });
    return itemTemplate;
};
var Dropdown = (function () {
    function Dropdown(name, options) {
        var self = this;
        self.name = name;
        self.toggleTemplate = options.toggleTemplate;
        self.$context = $(options.context); // element where  dropdown should be injected
        self.items = options.items;
        self.options = options;
        return self;
    }
    Dropdown.prototype.renderMenu = function (items) {
        var self = this;
        var itemsStr = '';
        items.forEach(function (item) {
            itemsStr += item.render();
        });
        var data = {};
        data.id = _1.default.camelCase2Dash(self.name) + '-menu';
        data.class = _1.default.camelCase2Dash(self.name) + '-menu-class';
        data.content = itemsStr;
        return menu_html_1.default(data);
    };
    Dropdown.prototype.renderToggle = function (template, data) {
        return template(data);
    };
    Dropdown.prototype.renderDropdown = function () {
        var self = this;
        var toggleTemplate = self.toggleTemplate;
        var data = {};
        data.toggle = this.renderToggle(toggleTemplate, {});
        data.menu = this.renderMenu(self.items);
        self.$context.append(dropdown_html_1.default(data));
        return self;
    };
    return Dropdown;
}());
Object.defineProperty(exports, "__esModule", { value: true });
exports.default = {
    dropdown: Dropdown,
    navDropdown: function (context) {
        var options = {
            context: context,
            toggleTemplate: navToggle_html_1.default
        };
        var items = [];
        items.push(new Item("nav-user-item", "Users", item_html_1.default, function () { return console.log('Users clicked'); }), new Item("nav-separator", 'separator', separator_html_1.default), new Item("nav-tenant-item", 'Tenants', item_html_1.default, function () {
            $.get(tenantURL).then(function (data) {
                console.log(data);
            }, function () {
                console.log("fail");
            });
        }));
        options.items = items;
        return new Dropdown('navDropdown', options);
    }
};
//# sourceMappingURL=index.js.map