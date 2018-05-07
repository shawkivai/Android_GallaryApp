package com.example.user03.gallery_demo.main;

import android.os.Handler;
import android.os.HandlerThread;

import com.example.user03.gallery_demo.base.BasePresenter;

import java.io.File;
import java.util.ArrayList;

/**
 * Created by USER03 on 3/12/2018.
 */

public class MainPresenter extends BasePresenter<MainMvpView> {




    ArrayList<File> imageReader(File root) {
        ArrayList<File> a = new ArrayList<>();

        File[] files = root.listFiles();
        for (int i = 0; i<files.length; i++){
            if (files[i].isDirectory()){
                a.addAll(imageReader(files[i]));
            }else {
                if (files[i].getName().endsWith(".jpg")){
                    a.add(files[i]);
                }
            }
        }
        return a;
    }

}
