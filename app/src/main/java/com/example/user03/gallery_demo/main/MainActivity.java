package com.example.user03.gallery_demo.main;

import android.content.Intent;
import android.net.Uri;
import android.os.Environment;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import com.example.user03.gallery_demo.R;
import com.example.user03.gallery_demo.base.BaseActivity;
import com.example.user03.gallery_demo.viewimage.ViewImage;

import java.io.File;
import java.util.ArrayList;

public class MainActivity extends BaseActivity<MainMvpView, MainPresenter> implements MainMvpView, View.OnClickListener, AdapterView.OnItemClickListener {
    Handler foregroundHandler;
    Handler backgroundHandler;

    HandlerThread handlerThread;
    GridView gv;
    ArrayList<File> list;

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void startUI() {
        foregroundHandler = new Handler(Looper.getMainLooper());
        handlerThread = new HandlerThread("Background", Thread.MAX_PRIORITY);
        handlerThread.start();

        backgroundHandler = new Handler(handlerThread.getLooper());
        list = presenter.imageReader(Environment.getExternalStorageDirectory() );
        gv = findViewById(R.id.gridView);
        gv.setAdapter(new GridAdapter());
//        gv.setOnItemClickListener(this);

        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                startActivity(new Intent(getApplicationContext(),ViewImage.class).putExtra("img",list.get(position).toString()));


            }
        });

        backgroundThread();

    }

    @Override
    protected void stopUI() {

    }

    @Override
    protected MainPresenter initPresenter() {
        return new MainPresenter();
    }

//    @Override
//    protected void onCreate(Bundle savedInstanceState) {
//        super.onCreate(savedInstanceState);
//        setContentView(R.layout.activity_main);
//        list = imageReader(Environment.getExternalStorageDirectory() );
//        gv = findViewById(R.id.gridView);
//        gv.setAdapter( new GridAdapter() );
//
//        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
//            @Override
//            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
//                startActivity(new Intent(getApplicationContext(),ViewImage.class).putExtra("img", list.get(position).toString()) );
//            }
//        });
//    }

//    ArrayList<File> imageReader(File root) {
//        ArrayList<File> a = new ArrayList<>();
//
//        File[] files = root.listFiles();
//        for (int i = 0; i<files.length; i++){
//            if (files[i].isDirectory()){
//                a.addAll(imageReader(files[i]));
//            }else {
//                if (files[i].getName().endsWith(".jpg")){
//                    a.add(files[i]);
//                }
//            }
//        }
//        return a;
//    }

    @Override
    public void onClick(View v) {

    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        startActivity(new Intent(getApplicationContext(), ViewImage.class).putExtra("img", list.get(position)));
    }

    class GridAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return list.size();
        }

        @Override
        public Object getItem(int position) {
            return list.get(position);
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            convertView = getLayoutInflater().inflate(R.layout.single_grid, parent, false);
            ImageView iv = convertView.findViewById(R.id.imageView);

            iv.setImageURI(Uri.parse(getItem(position).toString() ));


            return convertView;
        }
    }

    /*****************Simple thread ****************/

    private void foregroundThread(){
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
//                Toast.makeText(MainActivity.this, "Button Click",Toast.LENGTH_LONG).show();
            }
        };

        foregroundHandler.post(runnable);
    }


    private void backgroundThread(){
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(3000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
//                Toast.makeText(MainActivity.this, "Button Click",Toast.LENGTH_LONG).show();
            }
        };

        backgroundHandler.post(runnable);
    }
}
