package com.gorillamoa.happyBirthday.android.fragment;

import android.app.DialogFragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TimePicker;

import com.gorillamoa.happyBirthday.android.AndroidLauncher;
import com.gorillamoa.happyBirthday.android.R;
import com.gorillamoa.happyBirthday.android.notification.ScheduleClient;

/**
 * Created by alvaregd on 16/06/15.
 */
public class NotificationSetupDialog extends DialogFragment {

    View rootView;

    TimePicker time;

    AndroidLauncher.OnSetAlarmListener mListener ;

    public static NotificationSetupDialog newInstance(AndroidLauncher.OnSetAlarmListener mListener){
        NotificationSetupDialog dialog = new NotificationSetupDialog();
        dialog.mListener = mListener;
        return dialog;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStyle(android.support.v4.app.DialogFragment.STYLE_NO_TITLE, android.R.style.Theme_Holo_Light_Dialog);
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (rootView == null) {
            rootView= inflater.inflate(R.layout.notification, container,false);
        }

        time= (TimePicker)rootView.findViewById(R.id.tp_time);
        time.setCurrentHour(19);
        time.setCurrentMinute(30);

       rootView.findViewById(R.id.b_exit).setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {

               //Create notification
               mListener.setAlarm(time.getCurrentHour(),time.getCurrentMinute());
               dismiss();
           }
       });

        return rootView;
    }

    @Override
    public void onStop() {
        super.onStop();
    }
}
