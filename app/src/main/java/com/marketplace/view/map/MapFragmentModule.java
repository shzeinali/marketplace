package com.marketplace.view.map;

import com.marketplace.data.DataManager;
import com.marketplace.view.home.HomeViewModel;

import dagger.Module;
import dagger.Provides;

/**
 * Created by shima.zeinali on 5/19/2019.
 * shima.zeinalii@gmail.com
 */
@Module
public class MapFragmentModule {
    @Provides
    HomeViewModel provideMapViewModel(DataManager dataManager) {
        return new HomeViewModel(dataManager);
    }
}
