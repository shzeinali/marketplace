package com.marketplace.view.home;

import android.arch.lifecycle.ViewModelProvider;

import com.marketplace.data.DataManager;
import com.marketplace.view.base.ViewModelProviderFactory;

import dagger.Module;
import dagger.Provides;

/**
 * Created by shima.zeinali on 5/16/2019.
 * shima.zeinalii@gmail.com
 */
@Module
public class HomeActivityModule {
    @Provides
    HomeViewModel provideHomeViewModel(DataManager dataManager) {
        return new HomeViewModel(dataManager);
    }

    @Provides
    ViewModelProvider.Factory provideHomeViewModelFactory(HomeViewModel homeViewModel) {
        return new ViewModelProviderFactory<>(homeViewModel);
    }
}
