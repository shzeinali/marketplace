package com.marketplace.view.products;

import com.marketplace.data.DataManager;

import dagger.Module;
import dagger.Provides;

/**
 * Created by shima.zeinali on 5/17/2019.
 * shima.zeinalii@gmail.com
 */
@Module
public class ProductFragmentModule {
    @Provides
    ProductViewModel provideProductViewModel(DataManager dataManager) {
        return new ProductViewModel(dataManager);
    }

    @Provides
    ProductAdapter provideProductAdapter(ProductFragment fragment) {
        return new ProductAdapter(fragment.getActivity(), fragment.listener);
    }
}
