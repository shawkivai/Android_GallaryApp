package com.example.user03.gallery_demo.base;

import android.arch.lifecycle.Lifecycle;
import android.arch.lifecycle.LifecycleObserver;
import android.os.Bundle;


/**
 * Base class that implements the Presenter interface and provides a base implementation for
 * attachView() and detachView(). It also handles keeping a reference to the mvpView that
 * can be accessed from the children classes by calling getMvpView().
 */
public abstract class BasePresenter<V extends MvpView> implements LifecycleObserver, Presenter<V> {

    protected String TAG = getClass().getSimpleName();
    private V mMvpView;
    private Bundle stateBundle;
    @Override
    public void attachView(V mvpView) {
        mMvpView = mvpView;
    }
    @Override
    public void detachView() {
        mMvpView = null;

    }
    @Override
    public void attachLifecycle(Lifecycle lifecycle) {
        lifecycle.addObserver(this);
    }
    @Override
    public void detachLifecycle(Lifecycle lifecycle) {
        lifecycle.removeObserver(this);
    }
    public boolean isViewAttached() {
        return mMvpView != null;
    }

    /**
     * get MVP view of the Activity
     *
     * @return
     */
    public V getMvpView() {
        return mMvpView;
    }

    /**
     * get Activity bundle state
     *
     * @return
     */
    public Bundle getStateBundle() {
        return stateBundle == null ?
                stateBundle = new Bundle() : stateBundle;
    }
    public void checkViewAttached() {
        if (!isViewAttached()) throw new MvpViewNotAttachedException();
    }

    public static class MvpViewNotAttachedException extends RuntimeException {
        public MvpViewNotAttachedException() {
            super("Please call Presenter.attachView(MvpView) before" +
                    " requesting data to the Presenter");
        }
    }
    @Override
    public void onPresenterDestroy() {
        if (stateBundle != null && !stateBundle.isEmpty()) {
            stateBundle.clear();
        }
    }
    @Override
    public void onPresenterCreated() {
    }
}

