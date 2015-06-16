package com.gorillamoa.happyBirthday.android.alarm;

import android.app.AlarmManager;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.RingtoneManager;
import android.net.Uri;
import android.os.PowerManager;
import android.preference.PreferenceManager;
import android.widget.Toast;

import com.gorillamoa.happyBirthday.android.AndroidLauncher;
import com.gorillamoa.happyBirthday.android.R;

import java.util.Calendar;

/** our broadcast receiver alarm will set the alarm for us.
 * onReceive will be called whenever the alarm is triggered at some
 * time in the future
 */
public class Alarm extends BroadcastReceiver {

    public static final int NOTIFICATION = 123;

    public final static String PREF_MINUTE = "MINUTE";
    public final static String PREF_HOUR = "HOUR";
    public final static String PREF_FIRST = "FIRST";
    public final static String PREF_ON = "ON";
    public final static String PREF_SOUNDED = "SOUNDED";

    private NotificationManager mNotificationManager;
    private SharedPreferences prefs;
    private SharedPreferences.Editor editor;

    @Override
    public void onReceive(Context context, Intent intent) {

        PowerManager pm = (PowerManager) context.getSystemService(Context.POWER_SERVICE);
        showNotification(context);


    /*    *//** turn on the screen if it is off *//*
        if(!pm.isScreenOn())
        Intent main = new Intent(context, AndroidLauncher.class);
        main.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        context.getApplicationContext().startActivity(main);*/

    }

    public void setAlarm(Context context){

        /** Create a recursive alarm */
        AlarmManager am = (AlarmManager)context.getSystemService(Context.ALARM_SERVICE);
        Intent i = new Intent(context, Alarm.class);
        PendingIntent pi = PendingIntent.getBroadcast(context,0,i,0);

        /** get our shared preferes which will have our time */

        if(prefs == null){
            prefs = PreferenceManager.getDefaultSharedPreferences(context);
        }

        int minute = prefs.getInt(PREF_MINUTE,0);
        int hour = prefs.getInt(PREF_HOUR,0);

        Calendar c= Calendar.getInstance();
        c.set(Calendar.HOUR_OF_DAY, hour);
        c.set(Calendar.MINUTE, minute);

        /** get the current day of the year, set next day the alarm will go off to tomorrow */
        c.set(Calendar.DAY_OF_YEAR,
                (c.get(Calendar.DAY_OF_YEAR) == 364) ? 0 :
                        prefs.getBoolean(PREF_FIRST, false) ? c.get(Calendar.DAY_OF_YEAR) : c.get(Calendar.DAY_OF_YEAR) + 1);

        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean(PREF_ON,true);
        editor.putBoolean(PREF_FIRST, false);




        editor.commit();

        /*Log.i("Next Alarmt At:",
                c.get(Calendar.DAY_OF_WEEK)
                        + ", "
                        + c.get(Calendar.MONTH)
                        + " "

                        + c.get(Calendar.DAY_OF_MONTH)
                        //+  DeskApplication.getTesterInstance().getRealDay()
                        + " at "
                        + c.get(Calendar.HOUR_OF_DAY)
                        + ":"
                        + c.get(Calendar.MINUTE)
        );
*/
        am.set(AlarmManager.RTC_WAKEUP,c.getTimeInMillis(),pi);
    }

    public void cancelAlarm(Context context){
        Intent intent = new Intent(context,Alarm.class);
        PendingIntent sender = PendingIntent.getBroadcast(context,0,intent,0);
        AlarmManager alarmManager = (AlarmManager)context
                .getSystemService(Context.ALARM_SERVICE);
        alarmManager.cancel(sender);

        if(prefs == null){
            prefs = PreferenceManager.getDefaultSharedPreferences(context);
        }
        SharedPreferences.Editor editor = prefs.edit();
        editor.putBoolean(PREF_ON, false);
        editor.commit();

    }

    public void showNotification(Context context){



        Uri soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION);
        long[] vibrationPattern = {0, 500,250,500};

        mNotificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        CharSequence title = "Your Daily Surprise!";
        CharSequence text = "";

        /** set the intent*/
        Intent intent = new Intent(context, AndroidLauncher.class);
        intent.putExtra("INTENT", true);
        intent.setFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP | Intent.FLAG_ACTIVITY_CLEAR_TOP);

        PendingIntent contenIntent = PendingIntent.getActivity(context, 0,
                intent, PendingIntent.FLAG_UPDATE_CURRENT);

        /** build the notification */
        Notification notification;

        Notification.Builder builder = new Notification.Builder(context);
        builder.setSmallIcon(R.drawable.ic_launcher);//TODO get a cool icon
        builder.setContentTitle(title);
        builder.setContentText(text);
        builder.setContentIntent(contenIntent);
        builder.setOngoing(false);


        notification = builder.build();
        notification.flags = Notification.FLAG_AUTO_CANCEL;

        mNotificationManager.notify(NOTIFICATION, notification);

        Toast.makeText(context,"You are the best mom in the world!",Toast.LENGTH_LONG).show();
    }


}
