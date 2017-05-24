'use strict';

import Helpers from '../helper';

function log(prefix = '', event, data, callback) {
  if (!DEBUG) {
    return;
  }

  let logStr = event;

  if (typeof data === 'function') {
    callback = data;
    data = undefined;
  }

  if (data) {
    logStr += ' with data ' + JSON.stringify(data, function(key, value) {
        if (Helpers.isObject(value)) {
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
  log
};
