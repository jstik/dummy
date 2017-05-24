const CHARACTERS_MAP = {
  '&': '&amp;',
  '<': '&lt;',
  '>': '&gt;',
  '"': '&quot;',
  "'": '&#039;',
  "`": '&#096;'
};

class Helper {
  static camelCase2Dash(str) {
    return str.replace(/([a-z])([A-Z])/g, '$1-$2').toLowerCase();
  }
  static isObject(any) {
    return any &&
      typeof any === 'object' &&
      !Array.isArray(any) &&
      any !== null;
  }
  static createCID() {
    // 1e10, not more, fix for Microsoft Edge and IE
    return (Math.random() * 1e10).toString(36).split('.').join('');
  }

}
module.exports = Helper;
