phonegap-postdata-plugin
========================

post data to the remote server  
now only for android, the future will support ios  

Install
=======
1.  copy `src` floder to you project `src` floder
1.  copy `PostData.js` to `www` directory

use
===
include the `PostData.js` script to the project  
  
    var PostData = cordova.require("cordova/plugin/PostData");
    PostData({
      url: 'http://192.168.1.1/post.php',  //post url
      comment: 'comments'  // other params
    }, function(response) {
      //return string response
    });

Version
=======
0.0.1 - create


License
=======

[MIT](http://opensource.org/licenses/MIT)
