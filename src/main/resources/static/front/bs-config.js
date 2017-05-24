// bs-config.js
"use strict";

var proxy = require('http-proxy-middleware');

var tenantProxy = proxy('/v1/tenant/*', {
  target: 'http://localhost:8080',
  changeOrigin: true   // for vhosted sites
});

/// Export configuration options
module.exports = {
  /*"files" : "./src/!**!/!*.{js, html, css}",*/
  "server" : {
    "baseDir" : "./src",
    "middleware": {
      1: tenantProxy
    }
  },
  "https" : false
}
