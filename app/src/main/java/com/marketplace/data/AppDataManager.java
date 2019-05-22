package com.marketplace.data;

import android.os.Handler;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.marketplace.data.model.Category;
import com.marketplace.data.model.Order;
import com.marketplace.data.model.Product;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by shima.zeinali on 5/16/2019.
 * shima.zeinalii@gmail.com
 */
@Singleton
public class AppDataManager implements DataManager {
    final static int DELAY_MILLIS = 30000;
    private static final String CATEGORY_JSON = "[{\"name\":\"Category1\",\"icon\":\"ic_c1\"},{\"name\":\"Category2\",\"icon\":\"ic_c2\"},{\"name\":\"Category3\",\"icon\":\"ic_c3\"},{\"name\":\"Category4\",\"icon\":\"ic_c4\"},{\"name\":\"Category5\",\"icon\":\"ic_c5\"}]";
    private static final String PRODUCT_JSON = "[{\"name\":\"Product1\",\"icon\":\"ic_c2\"},{\"name\":\"Product2\",\"icon\":\"ic_c4\"},{\"name\":\"Product3\",\"icon\":\"ic_c1\"},{\"name\":\"Product4\",\"icon\":\"ic_c3\"},{\"name\":\"Product5\",\"icon\":\"ic_c5\"},{\"name\":\"Product6\",\"icon\":\"ic_c4\"},{\"name\":\"Product7\",\"icon\":\"ic_c1\"},{\"name\":\"Product8\",\"icon\":\"ic_c2\"}]";
    private final Gson gson;
    private List<Product> productList = new ArrayList<>();
    private List<Order> orderList = new ArrayList<>();

    @Inject
    public AppDataManager(Gson gson) {
        this.gson = gson;
    }

    @Override
    public List<Category> getCategoryList() {
        TypeToken<List<Category>> token = new TypeToken<List<Category>>() {
        };
        return gson.fromJson(CATEGORY_JSON, token.getType());
    }

    @Override
    public List<Product> getProductList() {
        if (productList.isEmpty()) {
            TypeToken<List<Product>> token = new TypeToken<List<Product>>() {
            };
            productList = gson.fromJson(PRODUCT_JSON, token.getType());
        }
        return productList;
    }

    @Override
    public List<Order> getOrderList() {
        return orderList;
    }

    @Override
    public void insertOrder(final Order order) {
        orderList.add(order);
        startHandler(orderList.size() - 1);
    }

    @Override
    public Product getProduct(int position) {
        if (productList == null)
            productList = getProductList();
        return productList.get(position);
    }

    private void startHandler(final int pos) {
        final Handler handler = new Handler();
        final Runnable runnable = new Runnable() {
            int i = 0;

            @Override
            public void run() {
                orderList.get(pos).status = i;
                if (i == 3)
                    handler.removeCallbacks(this);
                else {
                    i++;
                    handler.postDelayed(this, DELAY_MILLIS);
                }
            }
        };
        handler.post(runnable);
    }
}
