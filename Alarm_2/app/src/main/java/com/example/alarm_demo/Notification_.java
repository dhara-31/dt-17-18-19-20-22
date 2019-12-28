package com.example.alarm_demo;

import android.annotation.TargetApi;
import android.app.Notification;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.content.ContextWrapper;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v4.app.NotificationCompat;

public class Notification_ extends ContextWrapper
{
    private static final String channelID="channelID";
    private static final String channelName="channel Name";

    private NotificationManager notificationManager;


    public Notification_(Context base) {
        super(base);
        if (Build.VERSION.SDK_INT>=Build.VERSION_CODES.O){
            createchannel();

        }
    }
    @RequiresApi(api = Build.VERSION_CODES.O)
    private void createchannel()
    {
        NotificationChannel channel=new NotificationChannel(channelID,channelName,NotificationManager.IMPORTANCE_HIGH);

        getManager().createNotificationChannel(channel);

    }
    public NotificationManager getManager()
    {
        if (notificationManager==null){
            notificationManager=(NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        }
        return notificationManager;
    }
    public NotificationCompat.Builder getchannel()
    {
        return new NotificationCompat.Builder(getApplicationContext(),channelID).setContentTitle("Alarm..").setContentTitle("Your Alarm is Working..").setSmallIcon(R.drawable.ic_alarm_black_24dp);
    }
}