package com.gorillamoa.happyBirthday.android.notification;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.IBinder;

/**
 * This class is the wrapper class for binding
 * to the scheduleclient service
 */
public class ScheduleClient {

    private ScheduleService mBoundService; //our service
    private ServiceConnection mConnection; //conncetion interface

    private Context context; //context to start service in
    private boolean isBound; //a flag if we are connected to the service

    Intent intent;

    public ScheduleClient(Context context){
        this.context = context;
    }

    /** call this to connect the activity to our service */
    public void doBindService(){

        //estabalish a connection with our service

        mConnection  = new ServiceConnection() {
            @Override
            public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
                mBoundService = ((ScheduleService.ServiceBinder)iBinder).getService();
            }

            @Override
            public void onServiceDisconnected(ComponentName componentName) {
                mBoundService = null;
            }
        };

        intent = new Intent("com.gorillamoa.inten.action.NOTIFY_SERVICE");
        intent.addCategory("com.gorillamoa.inten.action.NOTIFY_SERVICE");
        context.bindService(intent,mConnection,Context.BIND_AUTO_CREATE);
        isBound = true;
    }

    public void doUnbindService(){
        if(isBound){
            context.unbindService(mConnection);
            isBound = false;
        }
    }

    /** Tell our service to set an alarm for the given date */
    public void setAalarmForNotification(){
        mBoundService.onStart(context, intent,01);
    }

    public void cancelAlarm(){
        mBoundService.onCancel(context);
    }


}
