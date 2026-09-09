package com.nishant.SemIII;

import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.content.Context;
import android.media.AudioAttributes;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;

import com.getcapacitor.BridgeActivity;

public class MainActivity extends BridgeActivity {

    private static final String CLASS_REMINDER_CHANNEL = "class-reminders-v2";

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        createClassReminderChannel();
    }

    private void createClassReminderChannel() {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.O) {
            return;
        }

        NotificationManager manager =
                (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        if (manager == null) {
            return;
        }

        Uri defaultSound =
                RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);

        AudioAttributes audioAttributes = new AudioAttributes.Builder()
                .setUsage(AudioAttributes.USAGE_NOTIFICATION)
                .setContentType(AudioAttributes.CONTENT_TYPE_SONIFICATION)
                .build();

        NotificationChannel channel = new NotificationChannel(
                CLASS_REMINDER_CHANNEL,
                "Class reminders",
                NotificationManager.IMPORTANCE_HIGH
        );

        channel.setDescription(
                "Important reminders before your classes"
        );

        channel.setSound(defaultSound, audioAttributes);
        channel.enableVibration(true);
        channel.setShowBadge(true);

        manager.createNotificationChannel(channel);
    }
}