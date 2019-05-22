package com.marketplace.view.orders;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.marketplace.R;
import com.marketplace.data.model.Order;
import com.marketplace.view.base.BaseRecyclerViewAdapter;
import com.marketplace.view.orders.items.OnOrderItemClickListener;
import com.marketplace.view.orders.items.OrderViewHolder;

/**
 * Created by shima.zeinali on 5/20/2019.
 * shima.zeinalii@gmail.com
 */
public class OrderAdapter extends BaseRecyclerViewAdapter<Order, OnOrderItemClickListener, OrderViewHolder> {
    public OrderAdapter(Context context, OnOrderItemClickListener listener) {
        super(context, listener);
    }

    @NonNull
    @Override
    public OrderViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_order, parent, false);
        return new OrderViewHolder(view, getListener());
    }
}
