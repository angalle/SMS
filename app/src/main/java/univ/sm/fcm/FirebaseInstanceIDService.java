//package univ.sm.fcm;
//
//import android.content.Intent;
//import android.util.Log;
//
//import com.google.firebase.iid.FirebaseInstanceId;
//import com.google.firebase.messaging.FirebaseMessagingService;
//import com.google.firebase.messaging.RemoteMessage;
//
//public class FirebaseInstanceIDService extends FirebaseMessagingService {
//	private static final String TAG = "InstanceIdService";
//
//
//
//	@Override
//	public void handleIntent(Intent intent) {
//		super.handleIntent(intent);
//	}
//
//	@Override
//	public void onMessageSent(String token) {
//		super.onMessageSent(token);
//		Log.e("Refreshed token:",token);
//
//		String refreshedToken = FirebaseInstanceId.getInstance().getToken();
//
//		Log.d(TAG, "Refreshed token: " + refreshedToken);
//
//		sendRegistrationToServer(refreshedToken);
//		// TODO Auto-generated method stub
////		Intent intent = new Intent(this, RegistrationIntentService.class);
////		startService(intent);
//	}
//
//	@Override
//	public void onMessageReceived(RemoteMessage remoteMessage) {
//		super.onMessageReceived(remoteMessage);
//	}
//
//	private void sendRegistrationToServer(String token) {
//
//	}
//}
