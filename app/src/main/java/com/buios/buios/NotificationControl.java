package com.buios.buios;

import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Build.VERSION_CODES;
import androidx.annotation.RequiresApi;
import androidx.core.app.NotificationCompat;
import androidx.core.app.NotificationCompat.Builder;

public class NotificationControl {

  final NotificationManager notificationManager;
  static int noti_num = 0;
  public static final String CHANEL_ID = "1001";
  private NotificationCompat.Builder builder;
  private final Context context;
  private String title, subtitle;

  public NotificationControl(Context context) {
    this.context = context;
    notificationManager = (NotificationManager) context
        .getSystemService(Context.NOTIFICATION_SERVICE);
  }


  public void sendNotify(String title, String subtitle) {
    this.title = title;
    this.subtitle = subtitle;

    builder = new Builder(context, CHANEL_ID)
        .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.drawable.img_seafood))
        .setContentTitle(title)
        .setContentText(subtitle)
        .setPriority(NotificationCompat.PRIORITY_DEFAULT)
        .setAutoCancel(true);

    if (VERSION.SDK_INT >= Build.VERSION_CODES.O) {
      createNotificationChannel();
    }

    Notification noti = builder.build();
    noti.flags |= Notification.FLAG_AUTO_CANCEL;

    noti_num++;
    this.notificationManager.notify(noti_num, noti);
  }

  @RequiresApi(api = VERSION_CODES.O)
  private void createNotificationChannel() {
    builder.setSmallIcon(R.drawable.img_seafood);
    CharSequence channelName = this.title;
    String description = this.subtitle;
    int importance = NotificationManager.IMPORTANCE_HIGH;

    NotificationChannel channel = new NotificationChannel(CHANEL_ID, channelName, importance);
    channel.setDescription(description);

    assert notificationManager != null;
    notificationManager.createNotificationChannel(channel);
  }
}
