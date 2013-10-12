package com.huaqin.punan.fragment4;

import android.app.Activity;
import android.content.res.Configuration;
import android.os.Bundle;

/**
 * Created by punan on 10/12/13.
 */
public class DetailsActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        if(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE){
            finish();
            return;
        }

        if(savedInstanceState == null){
            ArticleFragment articleFragment = new ArticleFragment();
            articleFragment.setArguments(getIntent().getExtras());
            getFragmentManager().beginTransaction().add(android.R.id.content,articleFragment).commit();
        }
    }
}
