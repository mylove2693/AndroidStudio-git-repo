package com.huaqin.punan.fragment4;

import android.app.Activity;
import android.app.ListFragment;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

/**
 * Created by punan on 10/12/13.
 */
public class HeadlinesFragment extends ListFragment {

    Resources mResource;
    OnHeadlineSelectedListener mCallback;

    boolean mDualPane;
    int mCurCheckPosition = 0;

    public interface OnHeadlineSelectedListener{
        public void onArticleSelected(int position);
    }

    @Override
    public void onListItemClick(ListView l, View v, int position, long id) {
        super.onListItemClick(l, v, position, id);
        mCurCheckPosition = position;
        mCallback.onArticleSelected(position);
    }

    @Override
    public void onActivityCreated(Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        mResource = getResources();
        String[] title = mResource.getStringArray(R.array.article_title);

        setListAdapter(new ArrayAdapter<String>(getActivity(),
                android.R.layout.simple_list_item_activated_1,title));

    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("index",mCurCheckPosition);
    }

    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);

        try {
            mCallback = (OnHeadlineSelectedListener) activity;
        } catch (ClassCastException e){
            throw new ClassCastException(activity.toString() +
                    "must implement OnHeadlineSelectedListener");
        }
    }
}
