package com.marketplace.view.orders;

import com.marketplace.data.DataManager;

import dagger.Module;
import dagger.Provides;

/**
 * Created by shima.zeinali on 5/20/2019.
 * shima.zeinalii@gmail.com
 */
@Module
public class OrderFragmentModule {
    @Provides
    OrderViewModel provideOrderViewModel(DataManager dataManager) {
        return new OrderViewModel(dataManager);
    }

    @Provides
    OrderAdapter provideOrderAdapter(OrderFragment fragment) {
        return new OrderAdapter(fragment.getActivity(), fragment.listener);
    }
}
