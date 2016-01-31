package xionces.com.gonemoney;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;

/**
 * Created by TayfunCESUR on 31.01.16.
 */
public class DateReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        if (intent.getAction().equals(Intent.ACTION_DATE_CHANGED)) {
            String strtitle = "Hey";
            String strtext = "Gün değişti";

            NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                    // Set Icon
                    .setSmallIcon(R.mipmap.smile)
                            // Set Ticker Message
                    .setTicker("Ticker")
                            // Set Title
                    .setContentTitle(strtitle)
                            // Set Text
                    .setContentText(strtext)
                            // Dismiss Notification
                    .setAutoCancel(true);

            // Create Notification Manager
            NotificationManager notificationmanager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);
            // Build Notification with Notification Manager
            notificationmanager.notify(0, builder.build());
        }
    }
}
