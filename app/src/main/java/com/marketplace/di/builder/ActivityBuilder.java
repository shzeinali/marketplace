package com.marketplace.di.builder;

import com.marketplace.view.category.CategoryFragmentProvider;
import com.marketplace.view.home.HomeActivity;
import com.marketplace.view.home.HomeActivityModule;
import com.marketplace.view.map.MapFragmentProvider;
import com.marketplace.view.orders.OrderFragmentProvider;
import com.marketplace.view.products.ProductFragmentProvider;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by shima.zeinali on 5/16/2019.
 * shima.zeinalii@gmail.com
 */
@Module
public abstract class ActivityBuilder {
    @ContributesAndroidInjector(modules = {
            HomeActivityModule.class,
            CategoryFragmentProvider.class,
            ProductFragmentProvider.class,
            MapFragmentProvider.class,
            OrderFragmentProvider.class})
    abstract HomeActivity bindHomeActivity();
}
