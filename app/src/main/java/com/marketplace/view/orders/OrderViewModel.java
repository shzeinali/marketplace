package com.marketplace.view.orders;

import android.arch.lifecycle.MutableLiveData;

import com.marketplace.data.DataManager;
import com.marketplace.data.model.Order;
import com.marketplace.view.base.BaseNavigator;
import com.marketplace.view.base.BaseViewModel;

import java.util.List;

/**
 * Created by shima.zeinali on 5/20/2019.
 * shima.zeinalii@gmail.com
 */
public class OrderViewModel extends BaseViewModel<BaseNavigator> {
    private MutableLiveData<List<Order>> orderListLiveData;

    public OrderViewModel(DataManager dataManager) {
        super(dataManager);
        updateList();
    }

    public MutableLiveData<List<Order>> getOrderListLiveData() {
        if (orderListLiveData == null)
            orderListLiveData = new MutableLiveData<>();
        return orderListLiveData;
    }

    public void updateList() {
        getOrderListLiveData().setValue(getDataManager().getOrderList());
    }
}
