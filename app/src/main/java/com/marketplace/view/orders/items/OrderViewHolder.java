package com.marketplace.view.orders.items;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.marketplace.R;
import com.marketplace.data.model.Order;
import com.marketplace.view.base.BaseViewHolder;

import butterknife.BindView;

/**
 * Created by shima.zeinali on 5/17/2019.
 * shima.zeinalii@gmail.com
 */
public class OrderViewHolder extends BaseViewHolder<Order, OnOrderItemClickListener> {
    @BindView(R.id.tv_name) TextView tvName;
    @BindView(R.id.iv_icon) ImageView ivIcon;
    @BindView(R.id.tv_status) TextView tvStatus;
    private Context context;

    public OrderViewHolder(View itemView, final OnOrderItemClickListener listener) {
        super(itemView, listener);
        context = itemView.getContext();
        if (listener != null) {
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(getAdapterPosition());
                }
            });
        }
    }

    @Override
    public void onBind(Order order) {
        ivIcon.setImageResource(context.getResources().getIdentifier(order.icon, "drawable", context.getPackageName()));
        tvName.setText(order.name);
        tvStatus.setText(Order.getStatus(order.status));
    }
}
