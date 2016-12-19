package univ.sm.gcm;

import android.content.Intent;

import com.google.android.gms.iid.InstanceIDListenerService;

public class MyInstanceIDListenerService extends InstanceIDListenerService {

	@Override
	public void onTokenRefresh() {
		// TODO Auto-generated method stub
		Intent intent = new Intent(this, RegistrationIntentService.class);
        startService(intent);
	}

}
