package org.apache.cordova.plugin;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.cordova.api.CallbackContext;
import org.apache.cordova.api.CordovaPlugin;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class PostData extends CordovaPlugin {
  @Override
    public boolean execute(String action, JSONArray args, final CallbackContext callbackContext) throws JSONException {
      if (action.equals("postdata")){
        JSONObject argsJSONObject = args.getJSONObject(0);
        this.postData(argsJSONObject, callbackContext);
        return true;
      }
      return false;
    }


  private void postData(JSONObject argsJSONObject, CallbackContext callbackContext) {   
    HttpClient httpclient = new DefaultHttpClient();
    String url = null;
    try {
      Iterator<String> iter = argsJSONObject.keys();
      Integer length = argsJSONObject.length();
      List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>(length);

      while(iter.hasNext()){
        String key = iter.next();
        Object value = argsJSONObject.get(key);
        if(key.equals("url")){
          url = (String) value;
        } else {
          nameValuePairs.add(new BasicNameValuePair(key, (String) value));
        }
      }

      HttpPost httppost = new HttpPost(url);

      httppost.setEntity(new UrlEncodedFormEntity(nameValuePairs));

      // Execute HTTP Post Request
      HttpResponse response = httpclient.execute(httppost);
      String responseBody = EntityUtils.toString(response.getEntity());

      if ( !responseBody.equals(null)) { 
        callbackContext.success(responseBody);
      } else {
        callbackContext.error("Expected one non-empty string argument.");
      }
    } catch (ClientProtocolException e) {

    } catch (IOException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    } catch (JSONException e) {
      // TODO Auto-generated catch block
      e.printStackTrace();
    }

  }
}
