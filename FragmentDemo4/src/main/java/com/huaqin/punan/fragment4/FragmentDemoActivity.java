package com.huaqin.punan.fragment4;

import android.app.Activity;
import android.app.FragmentTransaction;
import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.ListView;

public class FragmentDemoActivity extends Activity implements HeadlinesFragment.OnHeadlineSelectedListener{

    boolean mDualPane;
    int mCurCheckPosition = 0;

    private ListFragment headlinesFragment;
    private ArticleFragment articleFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_main4);

        View detailsFrame = findViewById(R.id.details);
        mDualPane = detailsFrame != null && detailsFrame.getVisibility() == View.VISIBLE;

        if (savedInstanceState != null) {
            mCurCheckPosition = savedInstanceState.getInt("index", 0);
        }

        if(mDualPane){
            headlinesFragment = (ListFragment)getFragmentManager().findFragmentById(R.id.titles);
            headlinesFragment.getListView().setChoiceMode(ListView.CHOICE_MODE_SINGLE);
            //onArticleSelected(mCurCheckPosition);
            headlinesFragment.getListView().setItemChecked(mCurCheckPosition,true);
            getFragmentManager().beginTransaction().add(R.id.details,
                    ArticleFragment.newInstance(mCurCheckPosition)).commit();
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putInt("index",mCurCheckPosition);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.fragment_demo, menu);
        return true;
    }

    @Override
    public void onArticleSelected(int position) {

        mCurCheckPosition = position;

        if(mDualPane){
            headlinesFragment.getListView().setItemChecked(position,true);

            if(articleFragment == null || articleFragment.getShownIndex() != position){
                articleFragment = ArticleFragment.newInstance(position);

                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.replace(R.id.details,articleFragment);
                ft.setTransition(FragmentTransaction.TRANSIT_FRAGMENT_FADE);
                ft.commit();
            }

        }else{
            Intent intent = new Intent();
            intent.setClass(this,DetailsActivity.class);
            intent.putExtra("index",position);
            startActivity(intent);
            //Toast.makeText(this, position + " new activity", Toast.LENGTH_SHORT).show();
        }

    }
}
