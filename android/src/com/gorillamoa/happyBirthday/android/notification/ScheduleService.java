package com.gorillamoa.happyBirthday.android.notification;

import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;

import com.gorillamoa.happyBirthday.android.alarm.Alarm;

/**
 * Service class, which will make notifications for us
 */
public class ScheduleService extends Service {

    Alarm alarm = new Alarm();

    public class ServiceBinder extends Binder {
        ScheduleService getService(){
            return ScheduleService.this;
        }
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        alarm.setAlarm(ScheduleService.this);
        return START_STICKY;
    }


    public void onStart(Context context, Intent intent, int startId){
        alarm.setAlarm(context);
    }

    public void onCancel(Context context){
        alarm.cancelAlarm(context);
    }

    @Override
    public IBinder onBind(Intent intent) {
        return mBinder;
    }

    private final IBinder mBinder = new ServiceBinder();




}
