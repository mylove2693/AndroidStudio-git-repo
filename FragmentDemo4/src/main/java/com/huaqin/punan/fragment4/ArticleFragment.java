package com.huaqin.punan.fragment4;

import android.app.Fragment;
import android.content.res.Resources;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ScrollView;
import android.widget.TextView;

/**
 * Created by punan on 10/12/13.
 */
public class ArticleFragment extends Fragment {

    Resources mResource;

    public static ArticleFragment newInstance(int index){
        ArticleFragment f = new ArticleFragment();

        Bundle args = new Bundle();
        args.putInt("index",index);
        f.setArguments(args);

        return f;
    }

    public int getShownIndex() {
        return getArguments().getInt("index", 0);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        if (container == null) {
            return null;
        }

        mResource = getResources();
        String[] article = mResource.getStringArray(R.array.article_detail);

        ScrollView scroller = new ScrollView(getActivity());
        TextView text = new TextView(getActivity());
        int padding = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP,
                4, getActivity().getResources().getDisplayMetrics());
        text.setPadding(padding, padding, padding, padding);
        scroller.addView(text);
        text.setText(article[getShownIndex()]);
        return scroller;
    }
}
