package com.marketplace.view.orders;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by shima.zeinali on 5/20/2019.
 * shima.zeinalii@gmail.com
 */

@Module
public abstract class OrderFragmentProvider {
    @ContributesAndroidInjector(modules = OrderFragmentModule.class)
    abstract OrderFragment provideOrderFragmentFactory();
}
