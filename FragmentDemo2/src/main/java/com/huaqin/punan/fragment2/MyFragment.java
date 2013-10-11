package com.huaqin.punan.fragment2;

import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by punan on 10/11/13.
 */
public class MyFragment extends Fragment {
    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        ViewGroup layout = (ViewGroup) inflater.inflate(R.layout.frag_layout, container, false);

        boolean shouldCreateChild = getArguments().getBoolean("shouldYouCreateAChildFragment");

        if (shouldCreateChild) {
            FragmentManager fm = getFragmentManager();
            FragmentTransaction ft = fm.beginTransaction();

            fm.beginTransaction();
            Fragment fragTwo = new MyFragment();
            Bundle arguments = new Bundle();
            arguments.putBoolean("shouldYouCreateAChildFragment", false);
            fragTwo.setArguments(arguments);
            ft.add(R.id.frag_container, fragTwo);
            ft.commit();

        }

        return layout;
    }
}
