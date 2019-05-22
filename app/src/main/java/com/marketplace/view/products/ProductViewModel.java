package com.marketplace.view.products;

import android.arch.lifecycle.MutableLiveData;

import com.marketplace.data.DataManager;
import com.marketplace.data.model.Product;
import com.marketplace.view.base.BaseNavigator;
import com.marketplace.view.base.BaseViewModel;

import java.util.List;

/**
 * Created by shima.zeinali on 5/17/2019.
 * shima.zeinalii@gmail.com
 */
public class ProductViewModel extends BaseViewModel<BaseNavigator> {
    private MutableLiveData<List<Product>> productListLiveData;

    public ProductViewModel(DataManager dataManager) {
        super(dataManager);
        fetchProducts();
    }

    private void fetchProducts() {
        if (productListLiveData == null)
            productListLiveData = new MutableLiveData<>();
        productListLiveData.setValue(getDataManager().getProductList());
    }

    public MutableLiveData<List<Product>> getProductListLiveData() {
        return productListLiveData;
    }
}
