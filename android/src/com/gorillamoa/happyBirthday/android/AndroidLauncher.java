package com.gorillamoa.happyBirthday.android;

import android.app.FragmentTransaction;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.gorillamoa.happyBirthday.android.activity.BaseActivity;
import com.gorillamoa.happyBirthday.android.alarm.Alarm;
import com.gorillamoa.happyBirthday.android.fragment.NotificationSetupDialog;
import com.gorillamoa.happyBirthday.android.notification.ScheduleClient;

import java.util.Calendar;
import java.util.Random;

public class AndroidLauncher extends BaseActivity  {

	CheckBox notification;
	private ScheduleClient scheduleClient;

	ImageView iv;
	TextView message;



	private boolean fromAlarm;

	public interface OnSetAlarmListener {
		 void setAlarm(int hour, int minute);
	}

	OnSetAlarmListener alarmSetListener = new OnSetAlarmListener() {
		@Override
		public void setAlarm(int hour, int minute) {
			SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
			SharedPreferences.Editor edit = prefs.edit();
			edit.putInt(Alarm.PREF_MINUTE,minute);
			edit.putInt(Alarm.PREF_HOUR, hour);
			edit.putBoolean(Alarm.PREF_FIRST, true);
			edit.commit();
			scheduleClient.setAalarmForNotification();
		}
	};


	@Override
	protected void onCreate (Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		Calendar c = Calendar.getInstance();

		if(c.get(Calendar.DAY_OF_MONTH) == 16 && c.get(Calendar.MONTH )== Calendar.JUNE){

		}else{
			setContentView(R.layout.main_activity);
//		AndroidApplicationConfiguration config = new AndroidApplicationConfiguration();
//		initialize(new Main(), config);

			notification = (CheckBox)findViewById(R.id.cb_notification);
			SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
			notification.setChecked(prefs.getBoolean(Alarm.PREF_ON,false));
			notification.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
				@Override
				public void onCheckedChanged(CompoundButton compoundButton, boolean b) {
					if (b) {
						FragmentTransaction ft = getFragmentManager().beginTransaction();
						NotificationSetupDialog newFragment = NotificationSetupDialog.newInstance(alarmSetListener);
						newFragment.show(ft, "yo");
					} else {
						scheduleClient.cancelAlarm();
					}
				}
			});

			/** determine whether to show surpise */

			iv = (ImageView)findViewById(R.id.iv_pic);
			message = (TextView)findViewById(R.id.tv_message);



			if(c.get(Calendar.HOUR_OF_DAY) >= prefs.getInt(Alarm.PREF_HOUR,0)
					&& c.get(Calendar.HOUR_OF_DAY) <= 23
					&& c.get(Calendar.MINUTE) >= prefs.getInt(Alarm.PREF_MINUTE,0)
					&& prefs.getBoolean(Alarm.PREF_ON,false)
					){

				message.setVisibility(View.VISIBLE);
				message.setText(getRandomString(c));
				iv.setVisibility(View.VISIBLE);
				iv.setImageResource(getRandomPicture(c));

			}


			/** set up the scheduler */
			scheduleClient = new ScheduleClient(this);
			scheduleClient.doBindService();
		}

	}

	@Override
	public void onStop() {
		if(scheduleClient != null){
			scheduleClient.doUnbindService();
		}

		super.onStop();
	}

	@Override
	protected void onNewIntent(Intent intent) {
		super.onNewIntent(intent);

		Bundle extras = intent.getExtras();
		fromAlarm = extras.getBoolean("INTENT");

	}

	public int getRandomPicture(Calendar calendar){

		switch (calendar.get(Calendar.DAY_OF_MONTH)){
			case 0: return R.drawable.img1;
			case 1: return R.drawable.img2;
			case 2: return R.drawable.img4;
			case 3: return R.drawable.img6;
			case 4: return R.drawable.img7;
			case 5: return R.drawable.img8;
			case 6: return R.drawable.img9;
			case 7: return R.drawable.img10;
			case 8: return R.drawable.img11;
			case 9: return R.drawable.img12;
			case 10: return R.drawable.img13;
			case 11: return R.drawable.img14;
			case 12: return R.drawable.img15;
			case 13: return R.drawable.img16;
			case 14: return R.drawable.img17;
			case 15: return R.drawable.img18;
			case 16: return R.drawable.img19;
			case 17: return R.drawable.img20;
			case 18: return R.drawable.img21;
			case 19: return R.drawable.img22;
			case 20: return R.drawable.img23;
			case 21: return R.drawable.img24;
			case 22: return R.drawable.img25;
			case 23: return R.drawable.img26;
			case 24: return R.drawable.img27;
			case 25: return R.drawable.img28;
			case 26: return R.drawable.img29;
			case 27: return R.drawable.img30;
			case 28: return R.drawable.img31;
			case 29: return R.drawable.img35;
			case 30: return R.drawable.img44;
			case 31: return R.drawable.img1;
		}

		return 0;
	}

	public String getRandomString(Calendar calendar){

		switch (calendar.get(Calendar.DAY_OF_MONTH)){
			case 0: return "You are the greated Mom in the world!";
			case 1: return "I love you - Guille";
			case 2: return "Never give up on your dreams!";
			case 3: return "Do you live only to pay the bills? Or do you live to live?";
			case 4: return "If you are going through hell, keep going";
			case 5: return "Life begins at the end of your comfort zone";
			case 6: return "Go towards fear";
			case 7: return "Failure is the path to Success";
			case 8: return "Don't be paralyzed by fear! Embrace it!";
			case 9: return "Get out of your head, and start living right now";
			case 10: return "You were created to do something nobody else can you";
			case 11: return "It is your duty to find you passion, otherwise you will rot inside.";
			case 12: return "There is no perfect moment, there is only now.";
			case 13: return "Feeling sad is only a part of life, don't fight it.";
			case 14: return "Your comfort zone is a prison within your mind";
			case 15: return "Never underestimate your ability to live your dreams";
			case 16: return "A 90 year old can be just as strong as a 20 year old, All you have to do is take care of your body";
			case 17: return "When you love yourself 100%, all other areas of life improve automatically.";
			case 18: return "Loving yourself means putting 100% of your effort into finding and doing your passion";
			case 19: return "You always have a choice of who you want to be";
			case 20: return "You know you have found your passion, when you get flow out of that activity";
			case 21: return "To wake up full of energy, you must have something to live for other than yourself";
			case 22: return "If you want to fly, you have to give up things that weigh you down";
			case 23: return "Man cannot remake himself without suffering, for he is both the marble and the sculpor";
			case 24: return "Its OK. You are OK. Everything has been OK since ever";
			case 25: return "The only limitations are those inside your mind";
			case 26: return "Your ego will prevent you from learning now things. Discard it";
			case 27: return "What we fear doing most is usually what we most need to do ";
			case 28: return "Imagine you are 99 years old and on your deatbed, suddenly you have a chance to come back to right now. Whatever you answer is what you should be doing";
			case 29: return "To find yourself, be free from the perception of others. You cannot do this without practise";
			case 30: return "Anxiety disappears when you learn to forgive yourself";
			case 31: return "Everything is mental. Its all mindset. Change your thoughts and it changes your life";
		}
		return "";
	}
}
