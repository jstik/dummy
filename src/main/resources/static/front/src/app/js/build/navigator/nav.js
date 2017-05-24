'use strict';
var brandTemplate_html_1 = require("./brandTemplate.html");
var _1 = require("../common/helper/");
var dropdown_1 = require("../common/dropdown");
function createNavigator(options) {
    return brandToHtml({ name: '' });
}
function brandToHtml(data) {
    return brandTemplate_html_1.default(data);
}
var HTMLComponent = (function () {
    function HTMLComponent(name, options) {
        if (options === void 0) { options = {}; }
        var self = this;
        self.$context = $(options.context);
        self.name = name;
        self.options = options;
        self.$dom = new Object(null);
    }
    HTMLComponent.prototype.render = function (callbacks) {
        var $self = this;
        $self.$context.addClass('page-' + _1.default.camelCase2Dash(self.name));
        $(function () {
            if (typeof callbacks === 'function') {
                callbacks = { main: callbacks };
            }
            var main = callbacks;
            $self.$context.append(callbacks.main());
        });
        return $self;
    };
    return HTMLComponent;
}());
document.addEventListener("DOMContentLoaded", function () {
    new HTMLComponent("brand", { context: ".container-fluid" })
        .render(createNavigator);
});
document.addEventListener("DOMContentLoaded", function () {
    dropdown_1.default.navDropdown('.navbar-right').renderDropdown();
});
//# sourceMappingURL=nav.js.map