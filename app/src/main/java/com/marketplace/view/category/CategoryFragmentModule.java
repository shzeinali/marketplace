package com.marketplace.view.category;

import com.marketplace.data.DataManager;

import dagger.Module;
import dagger.Provides;

/**
 * Created by shima.zeinali on 5/16/2019.
 * shima.zeinalii@gmail.com
 */
@Module
public class CategoryFragmentModule {
    @Provides
    CategoryViewModel provideCategoryViewModel(DataManager dataManager) {
        return new CategoryViewModel(dataManager);
    }

    @Provides
    CategoryAdapter provideCategoryAdapter(CategoryFragment fragment) {
        return new CategoryAdapter(fragment.getActivity(), fragment.listener);
    }
}
