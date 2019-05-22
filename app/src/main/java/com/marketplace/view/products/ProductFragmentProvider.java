package com.marketplace.view.products;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by shima.zeinali on 5/17/2019.
 * shima.zeinalii@gmail.com
 */
@Module
public abstract class ProductFragmentProvider {
    @ContributesAndroidInjector(modules = ProductFragmentModule.class)
    abstract ProductFragment provideProductFragmentFactory();
}
