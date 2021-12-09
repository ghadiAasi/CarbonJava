package com.example.carbonjava;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class NotificationReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Intent Intent = new Intent(context,NotificationService.class);
        context.startService(Intent);
    }
}
