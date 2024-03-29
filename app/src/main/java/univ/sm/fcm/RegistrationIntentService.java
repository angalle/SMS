package univ.sm.fcm;

import android.app.IntentService;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;
import android.util.Log;

import com.google.android.gms.gcm.GcmPubSub;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.iid.InstanceID;

import java.io.IOException;

import univ.sm.connect.GCMConnection;
import univ.sm.model.Const;


public class RegistrationIntentService extends IntentService{
	
	public RegistrationIntentService() {
		super(TAG);
		// TODO Auto-generated constructor stub
	}

	private static final String TAG = "RegIntentService";
	
    private static final String SENDER_ID = "474166243122";
	

	@Override
	protected void onHandleIntent(Intent intent) {
		SharedPreferences sharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        try {
            // [START register_for_gcm]
            // Initially this call goes out to the network to retrieve the token, subsequent calls
            // are local.
            // R.string.gcm_defaultSenderId (the Sender ID) is typically derived from google-services.json.
            // See https://developers.google.com/cloud-messaging/android/start for details on this file.
            // [START get_token]
//            InstanceID instanceID = InstanceID.getInstance(this);
//            String token = instanceID.getToken(SENDER_ID,
//                    GoogleCloudMessaging.INSTANCE_ID_SCOPE, null);
//            // [END get_token]
//            Log.i(TAG, "GCM Registration Token: " + token);
//
//            // TODO: Implement this method to send any registration to your app's servers.
//            sendRegistrationToServer(token);
//
//            // Subscribe to topic channels
//            subscribeTopics(token);

            // You should store a boolean that indicates whether the generated token has been
            // sent to your server. If the boolean is false, send the token to your server,
            // otherwise your server should have already received the token.
//            sharedPreferences.edit().putBoolean(QuickstartPreferences.SENT_TOKEN_TO_SERVER, true).apply();
            // [END register_for_gcm]
        } catch (Exception e) {
//            Log.d(TAG, "Failed to complete token refresh", e);
//            // If an exception happens while fetching the new token or updating our registration data
//            // on a third-party server, this ensures that we'll attempt the update at a later time.
//            sharedPreferences.edit().putBoolean(QuickstartPreferences.SENT_TOKEN_TO_SERVER, false).apply();
        }
        // Notify UI that registration has completed, so the progress indicator can be hidden.
//        Intent registrationComplete = new Intent(QuickstartPreferences.REGISTRATION_COMPLETE);
//        LocalBroadcastManager.getInstance(this).sendBroadcast(registrationComplete);
	}
	private static final String[] TOPICS = {"global"};
	
	private void subscribeTopics(String token) throws IOException {
		GcmPubSub pubSub = GcmPubSub.getInstance(this);
        for (String topic : TOPICS) {
            pubSub.subscribe(token, "/topics/" + topic, null);
        }
	}

	private void sendRegistrationToServer(String regid) {
		// TODO Auto-generated method stub
        SharedPreferences sp = getSharedPreferences(Const.SHARED_GCM, MODE_PRIVATE);
        SharedPreferences.Editor spe = sp.edit();
        spe.putString(Const.SHARED_REG_ID,regid);
        spe.commit();
		GCMConnection gcmCon = new GCMConnection(regid);

        gcmCon.HttpPostConnect(Const.BASE_URL);
	}
}
