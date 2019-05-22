package com.marketplace.data;

import com.marketplace.data.model.Category;
import com.marketplace.data.model.Order;
import com.marketplace.data.model.Product;

import java.util.List;

/**
 * Created by shima.zeinali on 5/16/2019.
 * shima.zeinalii@gmail.com
 */
public interface DataManager {
    List<Category> getCategoryList();

    List<Product> getProductList();

    List<Order> getOrderList();

    void insertOrder(Order order);

    Product getProduct(int id);
}
