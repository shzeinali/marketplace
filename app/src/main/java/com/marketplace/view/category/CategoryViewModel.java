package com.marketplace.view.category;

import android.arch.lifecycle.MutableLiveData;

import com.marketplace.data.DataManager;
import com.marketplace.data.model.Category;
import com.marketplace.view.base.BaseNavigator;
import com.marketplace.view.base.BaseViewModel;

import java.util.List;

/**
 * Created by shima.zeinali on 5/16/2019.
 * shima.zeinalii@gmail.com
 */
public class CategoryViewModel extends BaseViewModel<BaseNavigator> {
    private MutableLiveData<List<Category>> categoryListLiveData;

    public CategoryViewModel(DataManager dataManager) {
        super(dataManager);
        fetchCategory();
    }

    private void fetchCategory() {
        if (categoryListLiveData == null)
            categoryListLiveData = new MutableLiveData<>();
        categoryListLiveData.setValue(getDataManager().getCategoryList());
    }

    public MutableLiveData<List<Category>> getCategoryListLiveData() {
        return categoryListLiveData;
    }
}
