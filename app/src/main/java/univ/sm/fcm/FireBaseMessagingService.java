package univ.sm.fcm;

import android.util.Log;

import com.google.firebase.messaging.FirebaseMessagingService;
import com.google.firebase.messaging.RemoteMessage;


public class FireBaseMessagingService extends FirebaseMessagingService {
	private static final String TAG = "FireBaseMessaging";
    private static final String CHANNE_ID_ONE = "BOARD_NOTI";

    @Override
    public void onMessageReceived(RemoteMessage remoteMessage) {
        String message = remoteMessage.getData().get("message");
        String callBoardNo = remoteMessage.getData().get("call_board_no");
        String from = remoteMessage.getFrom();

        Log.d(TAG, "From: " + from);
        Log.d(TAG, "Message: " + message);
        Log.d(TAG, "CallBoardNo: " + callBoardNo);
        Log.d(TAG, "remoteMessage.getNotification() : " + remoteMessage.getNotification() );

        if (remoteMessage.getData() != null) {
            createNotification(from, message,callBoardNo);
        }
    }

	private void createNotification(String title, String body,String callNo) {
//        Intent intent = new Intent(this, BoardDetailView.class);
//        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//        intent.putExtra("board_no",callNo);
//        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent,PendingIntent.FLAG_ONE_SHOT);
//        String channelId = CHANNE_ID_ONE;
//        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
//
//        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this, channelId)
//                        .setSmallIcon(R.drawable.input_icon)
//                        .setContentTitle("SMS - 선문대셔틀버스 시간표")
//                        .setContentText(body)
//                        .setAutoCancel(true)
//                        .setSound(defaultSoundUri)
//                        .setContentIntent(pendingIntent);
//
//        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
//            String channelName = "게시물 알림";
//            NotificationChannel channel = new NotificationChannel(channelId, channelName, NotificationManager.IMPORTANCE_HIGH);
//            notificationManager.createNotificationChannel(channel);
//        }
//        notificationManager.notify(0 , notificationBuilder.build());
	}
}
