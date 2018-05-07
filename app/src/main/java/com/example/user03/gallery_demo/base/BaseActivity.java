package com.example.user03.gallery_demo.base;
import android.os.Bundle;
import android.support.annotation.CallSuper;
import android.support.v7.app.AppCompatActivity;

/*
*  ****************************************************************************
*  * Created by : Sudipta K Paik on 26-Aug-17 at 4:02 PM.
*  * Email : sudipta@w3engineers.com
*  *
*  * Responsibility: Abstract activity that every other Activity in this application must implement.
*  *
*  * Last edited by : Sudipta on 02-11-17.
*  *
*  * Last Reviewed by : <Reviewer Name> on <mm/dd/yy>
*  ****************************************************************************
*/
public abstract class BaseActivity<V extends MvpView, P extends BasePresenter<V>> extends AppCompatActivity implements MvpView {

    protected P presenter;
    private int mDefaultValue = -1;

    protected abstract int getLayoutId();
    protected abstract void startUI();
    protected abstract void stopUI();

    protected abstract P initPresenter();


    @SuppressWarnings("unchecked")
    @CallSuper
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        int layoutId = getLayoutId();
        if (layoutId > mDefaultValue) {
            setContentView(layoutId);
            presenter = initPresenter();
            presenter.attachView((V) this);
        }
        startUI();
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        stopUI();
    }

}
