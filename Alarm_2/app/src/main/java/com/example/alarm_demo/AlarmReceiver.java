package com.example.alarm_demo;

import android.app.AlertDialog;
import android.content.BroadcastReceiver;;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.view.WindowManager;

public class AlarmReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {

        Notification_ notification_=new Notification_(context);
        NotificationCompat.Builder builder=notification_.getchannel();
        notification_.getManager().notify(1,builder.build());



        Intent intent1 = new Intent(context, RingtonePlayingService.class);
        context.startService(intent1);
/*
        AlertDialog.Builder ab = new AlertDialog.Builder(context);
        ab.setMessage("Did the dialog display?");
        AlertDialog alertDialog =ab.create();
        alertDialog.show();
*/
    }
}