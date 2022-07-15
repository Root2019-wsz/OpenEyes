package com.wuchen.library_base.viewmodel;

import androidx.lifecycle.ViewModel;

import com.wuchen.library_base.model.SuperBaseModel;

import java.lang.ref.Reference;
import java.lang.ref.WeakReference;

public  abstract class MvmBaseViewModel<V, M extends SuperBaseModel> extends ViewModel
        implements IMvvmBaseViewModel<V> {

    private Reference<V> mUiRef;

    protected M model;

    @Override
    public void attachUi(V view)
    {
        mUiRef = new WeakReference<>(view);
    }

    @Override
    public V getPageView()
    {
        if (null == mUiRef)
        {
            return null;
        }
        if (null != mUiRef.get())
        {
            return mUiRef.get();
        }
        return null;
    }

    @Override
    public boolean isUiAttach()
    {
        return null != mUiRef && null != mUiRef.get();
    }

    @Override
    public void detachUi()
    {
        if (null != mUiRef)
        {
            mUiRef.clear();
            mUiRef = null;
        }
        if (null != model)
        {
            model.cancel();
        }
    }


    protected void loadData(){

    }

    protected  abstract void initModel();

}
