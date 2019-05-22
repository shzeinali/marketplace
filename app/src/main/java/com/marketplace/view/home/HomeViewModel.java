package com.marketplace.view.home;

import android.arch.lifecycle.MutableLiveData;

import com.marketplace.data.DataManager;
import com.marketplace.data.model.Order;
import com.marketplace.data.model.Product;
import com.marketplace.view.base.BaseNavigator;
import com.marketplace.view.base.BaseViewModel;

/**
 * Created by shima.zeinali on 5/16/2019.
 * shima.zeinalii@gmail.com
 */
public class HomeViewModel extends BaseViewModel<BaseNavigator> {
    private MutableLiveData<Boolean> ldTabStatus;

    public HomeViewModel(DataManager dataManager) {
        super(dataManager);
    }

    public MutableLiveData<Boolean> getTabStatus() {
        if (ldTabStatus == null)
            ldTabStatus = new MutableLiveData<>();
        return ldTabStatus;
    }

    private void redirectTab() {
        this.ldTabStatus.setValue(true);
    }

    private Order createOrder(Product product, String address) {
        return new Order.Builder()
                .setName(product.name)
                .setIcon(product.icon)
                .setAddress(address)
                .setStatus(Order.PENDING)
                .build();
    }

    private Product getProduct(int id) {
        return getDataManager().getProduct(id);
    }

    public void registerOrder(int id, String address) {
        Product product = getProduct(id);
        getDataManager().insertOrder(createOrder(product, address));
        redirectTab();
    }
}
