package com.bril.base.ui.notification;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.ContextWrapper;
import android.content.Intent;
import android.graphics.BitmapFactory;
import android.os.Build;
import android.support.v4.app.NotificationCompat;

import com.bril.base.R;

/**
 * Created by 123456 on 2017/2/16.
 */

public class NotificationUtils extends ContextWrapper {
    private NotificationManager notificationManager;
    public static final String ID = "channel_1";
    public static final String NAME = "channel_name_1";
    public static final String description = "notification_description";

    public NotificationUtils(Context base) {
        super(base);
    }

    @TargetApi(Build.VERSION_CODES.O)
    public void createNotificationChannel() {
        NotificationChannel channel = new NotificationChannel(ID, NAME, NotificationManager.IMPORTANCE_HIGH);
        channel.enableLights(true);//是否显示通知指示灯
        channel.enableVibration(true);//是否振动
        channel.setLockscreenVisibility(Notification.VISIBILITY_PRIVATE);//设置是否应在锁定屏幕上显示此频道的通知
        channel.setDescription(description);//设置渠道描述,这个描述在系统设置中可以看到。
        channel.setShowBadge(true);// 设置是否显示角标
        getManager().createNotificationChannel(channel);
    }

    private NotificationManager getManager() {
        synchronized (NotificationManager.class) {
            if (notificationManager == null) {
                notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            }
        }
        return notificationManager;
    }

    public void SendNoticeMsg(Context context, String ticker, String contentTitle, String content, Intent openInten, int id) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createNotificationChannel();
            Notification notification = getChannelNotification(context, ticker, contentTitle, content, openInten).build();
            notification.flags = Notification.FLAG_AUTO_CANCEL;
            getManager().notify(id, notification);
        } else {
            Notification notification = getNotification_25(context, ticker, contentTitle, content, openInten).build();
            notification.flags = Notification.FLAG_AUTO_CANCEL;
            getManager().notify(id, notification);
        }
//        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
//                .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher))
//                .setSmallIcon(R.mipmap.ic_launcher)
//                .setTicker(ticker)
//                .setContentTitle(contentTitle)
//                .setContentText(content)
//                .setPriority(Notification.PRIORITY_MAX)
//                .setVisibility(Notification.VISIBILITY_PRIVATE)
//                .setDefaults(Notification.DEFAULT_VIBRATE | Notification.DEFAULT_ALL | Notification.DEFAULT_SOUND)
//                .setAutoCancel(true);
//        if (openInten != null) {
//            openInten.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
//            PendingIntent pendingIntent = PendingIntent.getActivity(context, 10, openInten, 0);
//            builder.setContentIntent(pendingIntent);
//        }
//        Notification ntf = builder.build();
//        ntf.flags = Notification.FLAG_AUTO_CANCEL;
//        notificationManager.notify(id, ntf);
    }

    @TargetApi(Build.VERSION_CODES.O)
    public NotificationCompat.Builder getChannelNotification(Context context, String ticker, String contentTitle, String content, Intent openInten) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context, ID)
                .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher))
                .setSmallIcon(R.mipmap.ic_launcher)
                .setTicker(ticker)
                .setContentTitle(contentTitle)
                .setContentText(content)
                //.setNumber(3)
                .setBadgeIconType(Notification.BADGE_ICON_SMALL)
                .setPriority(Notification.PRIORITY_MAX)
                .setWhen(System.currentTimeMillis())
                .setVisibility(Notification.VISIBILITY_PRIVATE)
                .setDefaults(Notification.DEFAULT_VIBRATE | Notification.DEFAULT_ALL | Notification.DEFAULT_SOUND)
                .setAutoCancel(true);
        if (openInten != null) {
            openInten.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 10, openInten, PendingIntent.FLAG_UPDATE_CURRENT);
            builder.setContentIntent(pendingIntent);
        }
        return builder;

    }

    public NotificationCompat.Builder getNotification_25(Context context, String ticker, String contentTitle, String content, Intent openInten) {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setLargeIcon(BitmapFactory.decodeResource(context.getResources(), R.mipmap.ic_launcher))
                .setSmallIcon(R.mipmap.ic_launcher)
                .setTicker(ticker)
                //.setNumber(3)
                .setBadgeIconType(Notification.BADGE_ICON_SMALL)
                .setContentTitle(contentTitle)
                .setContentText(content)
                .setPriority(Notification.PRIORITY_MAX)
                .setWhen(System.currentTimeMillis())
                .setVisibility(Notification.VISIBILITY_PRIVATE)
                .setDefaults(Notification.DEFAULT_VIBRATE | Notification.DEFAULT_ALL | Notification.DEFAULT_SOUND)
                .setAutoCancel(true);
        if (openInten != null) {
            openInten.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP);
            PendingIntent pendingIntent = PendingIntent.getActivity(context, 10, openInten, PendingIntent.FLAG_UPDATE_CURRENT);
            builder.setContentIntent(pendingIntent);
        }

        return builder;
    }
}