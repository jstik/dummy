'use strict';
var helper_1 = require("../helper");
function log(prefix, event, data, callback) {
    if (prefix === void 0) { prefix = ''; }
    if (!DEBUG) {
        return;
    }
    var logStr = event;
    if (typeof data === 'function') {
        callback = data;
        data = undefined;
    }
    if (data) {
        logStr += ' with data ' + JSON.stringify(data, function (key, value) {
            if (helper_1.default.isObject(value)) {
                // Chrome circle issue
                return 'object';
            }
        });
    }
    if (callback) {
        logStr += ' with callback ' + callback.name || 'anonymous';
    }
    console.log('%c ' + prefix + ': ', 'color:black;font-weight:bold;', logStr);
}
module.export = {
    log: log
};
//# sourceMappingURL=index.js.map