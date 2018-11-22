package univ.sm.fcm;

import android.content.Intent;
import android.util.Log;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

public class FirebaseInstanceIDService extends FirebaseInstanceIdService {
	private static final String TAG = "InstanceIdService";

	@Override
	public void onTokenRefresh() {
		String refreshedToken = FirebaseInstanceId.getInstance().getToken();

		Log.d(TAG, "Refreshed token: " + refreshedToken);

		sendRegistrationToServer(refreshedToken);
		// TODO Auto-generated method stub
		Intent intent = new Intent(this, RegistrationIntentService.class);
        startService(intent);
	}

	private void sendRegistrationToServer(String token) {

	}
}
