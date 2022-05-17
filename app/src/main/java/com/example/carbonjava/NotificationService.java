package com.example.carbonjava;

import android.app.IntentService;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Build;

import androidx.core.app.NotificationManagerCompat;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>
 * <p>
 * helper methods.
 */
public class NotificationService extends IntentService {
    //this is the id for the notification
    private static final int NOTIFICATION_ID = 3;

    public NotificationService() {
        super("NotificationService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        Notification.Builder noBuilder = new Notification.Builder(this);
        noBuilder.setContentTitle("Common Games ");
        //if() {
            noBuilder.setContentTitle("Welcome back to Common games!");
        //}
        //else{

        //}
        noBuilder.setSmallIcon(R.drawable.ic_baseline_chat_24);
        //this intent will be pending until the user clicks on the notification and will activate the activity specified in the intent
        Intent noIntent = new Intent(this, LogInActivity.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 2, noIntent, PendingIntent.FLAG_UPDATE_CURRENT);
        noBuilder.setContentIntent(pendingIntent);
        Notification notification = noBuilder.build();

        NotificationManagerCompat managerCompat = NotificationManagerCompat.from(this);
        managerCompat.notify(NOTIFICATION_ID, notification);


        NotificationManager mNotificationManager;
        mNotificationManager =
                (NotificationManager) this.getSystemService(Context.NOTIFICATION_SERVICE);

        //this is required
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            String channelId = "Your_channel_id";
            NotificationChannel channel = new NotificationChannel(
                    channelId,
                    "Channel human readable title",
                    NotificationManager.IMPORTANCE_HIGH);
            mNotificationManager.createNotificationChannel(channel);
            noBuilder.setChannelId(channelId);
        }
        noBuilder.build();
        mNotificationManager.notify(NOTIFICATION_ID,notification);
    }
}