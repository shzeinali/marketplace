package com.marketplace.view.map;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by shima.zeinali on 5/17/2019.
 * shima.zeinalii@gmail.com
 */
@Module
public abstract class MapFragmentProvider {
    @ContributesAndroidInjector(modules = MapFragmentModule.class)
    abstract MapFragment provideMapFragmentFactory();
}
