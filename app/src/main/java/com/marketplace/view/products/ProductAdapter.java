package com.marketplace.view.products;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.marketplace.R;
import com.marketplace.data.model.Product;
import com.marketplace.view.base.BaseRecyclerViewAdapter;
import com.marketplace.view.products.items.OnProductItemClickListener;
import com.marketplace.view.products.items.ProductsViewHolder;

/**
 * Created by shima.zeinali on 5/17/2019.
 * shima.zeinalii@gmail.com
 */
public class ProductAdapter extends BaseRecyclerViewAdapter<Product, OnProductItemClickListener, ProductsViewHolder> {

    public ProductAdapter(Context context, OnProductItemClickListener listener) {
        super(context, listener);
    }

    @NonNull
    @Override
    public ProductsViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_product, parent, false);
        return new ProductsViewHolder(view, getListener());
    }
}
