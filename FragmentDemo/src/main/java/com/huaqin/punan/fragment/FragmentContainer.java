package com.huaqin.punan.fragment;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by punan on 10/10/13.
 */
public class FragmentContainer extends Fragment{

    private Button btn1;
    private Button btn2;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);

        ViewGroup layout = (ViewGroup)inflater.inflate(R.layout.fragment_container,container,false);

        final FragmentOne mFragmentOne = new FragmentOne();
        final FragmentTwo mFragmentTwo = new FragmentTwo();

        FragmentTransaction fragmentTransaction = getFragmentManager().beginTransaction();

        fragmentTransaction.add(R.id.frag_container,mFragmentTwo);
        fragmentTransaction.add(R.id.frag_container,mFragmentOne);

        fragmentTransaction.commit();


        btn1 = (Button)layout.findViewById(R.id.btn1);
        btn2 = (Button)layout.findViewById(R.id.btn2);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                if(mFragmentOne.isDetached())
                    ft.attach(mFragmentOne);
                if(!mFragmentTwo.isDetached())
                    ft.detach(mFragmentTwo);

                ft.commit();
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                if(!mFragmentOne.isDetached())
                    ft.detach(mFragmentOne);
                if(mFragmentTwo.isDetached())
                    ft.attach(mFragmentTwo);

                ft.commit();
            }
        });


        return layout;

    }

    @Override
    public void onPause() {
        super.onPause();
    }
}
