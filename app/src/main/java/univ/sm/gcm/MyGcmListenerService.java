package univ.sm.gcm;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.util.Log;

import com.google.android.gms.gcm.GcmListenerService;

import univ.sm.R;
import univ.sm.Splash;
import univ.sm.board.BoardDetailPage;


public class MyGcmListenerService extends GcmListenerService {
	public static final int MESSAGE_NOTIFICATION_ID = 435345;
	private static final String TAG = "MyGcmListenerService";
	@Override
	public void onMessageReceived(String from, Bundle data) {
		String message = data.getString("message");
        String callBoardNo = data.getString("call_board_no");
        Log.d(TAG, "From: " + from);
        Log.d(TAG, "Message: " + message);
        Log.d(TAG, "CallBoardNo: " + callBoardNo);

        if (from.startsWith("/topics/")) {
            // message received from some topic.
        } else {
            // normal downstream message.
        }
        createNotification(from, message,callBoardNo);
	}

	private void createNotification(String title, String body,String callNo) {
		Intent intent = new Intent(this, BoardDetailPage.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("board_no",callNo);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 , intent,PendingIntent.FLAG_ONE_SHOT);

        Uri defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        NotificationCompat.Builder notificationBuilder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.drawable.input_icon)
                .setContentTitle("SMS - 선문대셔틀버스 시간표")
                .setContentText(body)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent);

        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.notify(0 , notificationBuilder.build());


        /*PendingIntent pendingIntent = PendingIntent.getActivity(this, 0 *//*ID of notification *//*, intent,PendingIntent.FLAG_ONE_SHOT);
        notificationManager.notify(0 *//*Request code *//*, notificationBuilder.build());*/
	}

}
