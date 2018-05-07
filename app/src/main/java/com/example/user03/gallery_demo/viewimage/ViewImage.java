package com.example.user03.gallery_demo.viewimage;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;

import com.example.user03.gallery_demo.R;
import com.example.user03.gallery_demo.base.BaseActivity;

import java.io.File;
import java.util.ArrayList;

public class ViewImage extends BaseActivity<ViewImageMvpView, ViewImagePresenter> implements ViewImageMvpView, View.OnClickListener, AdapterView.OnItemClickListener {
    ImageView iv2;
    @Override
    protected int getLayoutId() {
        return R.layout.activity_view_image;
    }

    @Override
    protected void startUI() {
        Intent i = getIntent();
        String f = i.getStringExtra("img");
        iv2 = findViewById(R.id.image);
        iv2.setImageURI(Uri.parse( f ));
    }

    @Override
    protected void stopUI() {

    }

    @Override
    protected ViewImagePresenter initPresenter() {
        return new ViewImagePresenter();
    }

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_view_image);
//
//        Intent i = getIntent();
////        String f = i.getExtras().getParcelable("img").toString();
//        String f = i.getStringExtra("img");
//        iv2 = findViewById(R.id.image);
//        iv2.setImageURI(Uri.parse( f ));
//
//    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }
}
