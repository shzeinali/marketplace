package com.marketplace.view.category;

import dagger.Module;
import dagger.android.ContributesAndroidInjector;

/**
 * Created by shima.zeinali on 5/16/2019.
 * shima.zeinalii@gmail.com
 */
@Module
public abstract class CategoryFragmentProvider {
    @ContributesAndroidInjector(modules = CategoryFragmentModule.class)
    abstract CategoryFragment provideCategoryFragmentFactory();
}
