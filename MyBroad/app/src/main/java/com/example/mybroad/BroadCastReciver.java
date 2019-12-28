package com.example.mybroad;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

class BroadCastReciver  extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.e("time","tiem......");
        Toast.makeText(context, "Time Up.. !!!",
                Toast.LENGTH_LONG).show();

       /* Notification notification_=new Notification(context);
        NotificationCompat.Builder builder=notification_.getchannel();
        notification_.getManager().notify(1,builder.build());
*/
    }
}