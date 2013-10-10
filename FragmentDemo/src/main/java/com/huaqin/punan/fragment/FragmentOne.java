package com.huaqin.punan.fragment;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

/**
 * Created by punan on 10/10/13.
 */
public class FragmentOne extends Fragment{
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);

        View v = inflater.inflate(R.layout.activity_fragment,container,false);

        TextView textView = (TextView)v.findViewById(R.id.textview);

        textView.setText("Fragment One");

        return v;
    }

    @Override
    public void onPause() {
        super.onPause();
    }
}
