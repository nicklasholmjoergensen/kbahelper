package io.peqo.kbahelper.network;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

import io.peqo.kbahelper.R;

public class MyFirebaseNotificationManager {

    public static final int ID_BIG_NOTIFICATION = 234;
    public static final int ID_SMALL_NOTIFICATION = 235;

    private Context c;

    public MyFirebaseNotificationManager(Context c) {
        this.c = c;
    }

    public void showNotification(String title, String message, Intent intent) {
        PendingIntent resultPendingIntent = PendingIntent.getActivity(
                c,
                ID_SMALL_NOTIFICATION,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT
        );

        NotificationCompat.Builder builder = new NotificationCompat.Builder(c);
        Notification notification = builder
                .setSmallIcon(R.drawable.ic_info_black_24dp)
                .setTicker(title)
                .setWhen(0)
                .setAutoCancel(true)
                .setContentIntent(resultPendingIntent)
                .setContentTitle(title)
                .setContentText(message)
                .build();
        notification.flags |= Notification.FLAG_AUTO_CANCEL;

        NotificationManager manager = (NotificationManager) c.getSystemService(Context.NOTIFICATION_SERVICE);
        manager.notify(ID_SMALL_NOTIFICATION, notification);
    }

}
