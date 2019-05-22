package com.marketplace.view.base;

import android.arch.lifecycle.ViewModel;

import com.marketplace.data.DataManager;

import java.lang.ref.WeakReference;

/**
 * Created by shima.zeinali on 5/16/2019.
 * shima.zeinalii@gmail.com
 */
public class BaseViewModel<N> extends ViewModel {
    private final DataManager dataManager;
    private WeakReference<N> navigator;

    public BaseViewModel(DataManager dataManager) {
        this.dataManager = dataManager;
    }

    public DataManager getDataManager() {
        return dataManager;
    }

    public N getNavigator() {
        return navigator.get();
    }

    public void setNavigator(N navigator) {
        this.navigator = new WeakReference<>(navigator);
    }

    @Override
    protected void onCleared() {
        super.onCleared();
    }

}
