package com.gorillamoa.happyBirthday.android.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.badlogic.gdx.backends.android.AndroidFragmentApplication;
import com.gorillamoa.happyBirthday.Main;

/**
 * Created by alvaregd on 16/06/15.
 */
public class BackgroundFragment extends AndroidFragmentApplication {

    View rootView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if(rootView == null){
            rootView = initializeForView(new Main());
        }
        return rootView;
    }
}
