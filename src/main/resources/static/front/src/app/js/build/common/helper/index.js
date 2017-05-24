var CHARACTERS_MAP = {
    '&': '&amp;',
    '<': '&lt;',
    '>': '&gt;',
    '"': '&quot;',
    "'": '&#039;',
    "`": '&#096;'
};
var Helper = (function () {
    function Helper() {
    }
    Helper.camelCase2Dash = function (str) {
        return str.replace(/([a-z])([A-Z])/g, '$1-$2').toLowerCase();
    };
    Helper.isObject = function (any) {
        return any &&
            typeof any === 'object' &&
            !Array.isArray(any) &&
            any !== null;
    };
    Helper.createCID = function () {
        // 1e10, not more, fix for Microsoft Edge and IE
        return (Math.random() * 1e10).toString(36).split('.').join('');
    };
    return Helper;
}());
module.exports = Helper;
//# sourceMappingURL=index.js.map