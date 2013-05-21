cordova.define("cordova/plugin/PostData", function (require, exports, module) {
  var exec = require("cordova/exec");
  module.exports = function (args, win, fail) {
    exec(win, fail, "PostData", "postdata", [args]);
  };
});
