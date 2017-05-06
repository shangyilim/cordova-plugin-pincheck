package cordova.plugin.pincheck;

import android.app.KeyguardManager;
import android.content.Context;

import org.apache.cordova.CordovaPlugin;
import org.apache.cordova.CallbackContext;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 * This class echoes a string called from JavaScript.
 */
public class PinCheck extends CordovaPlugin {

    @Override
    public boolean execute(String action, JSONArray args, CallbackContext callbackContext) throws JSONException {
        if (action.equals("isPinSetup")) {
            this.pinCheck(callbackContext);
            return true;
        }
        return false;
    }

    private void pinCheck(CallbackContext callbackContext) {
        Context context = this.cordova.getActivity().getApplicationContext();
        boolean result = isKeyguardSecure(context);
        if(result) {
            callbackContext.success("PIN_SETUP");
        }
        else{
            callbackContext.error("NO_PIN_SETUP");
        }
    }
    
    private boolean isKeyguardSecure(Context context) {
        KeyguardManager keyguardManager = (KeyguardManager) context.getSystemService(Context.KEYGUARD_SERVICE);
        return keyguardManager.isKeyguardSecure();
    }
}
