package com.huaqin.punan.fragment2;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

/**
 * Created by punan on 10/11/13.
 */
public class MyFragment extends Fragment {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setHasOptionsMenu(true);

    }

    @Override
    public View onCreateView(final LayoutInflater inflater, final ViewGroup container, final Bundle savedInstanceState) {
        ViewGroup layout = (ViewGroup) inflater.inflate(R.layout.frag_layout, container, false);

        boolean shouldCreateChild = getArguments().getBoolean("shouldYouCreateAChildFragment");
        /*
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
        */
        return layout;
    }

    @Override
    public void onPrepareOptionsMenu(Menu menu) {
        super.onPrepareOptionsMenu(menu);
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        getActivity().getMenuInflater().inflate(R.menu.fragment_menu,menu);
        super.onCreateOptionsMenu(menu, inflater);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {

        Toast.makeText(getActivity(),"item:"+item.getItemId()+" clicked",Toast.LENGTH_SHORT).show();

        return super.onOptionsItemSelected(item);
    }
}
