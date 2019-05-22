package com.marketplace.view.products.items;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.marketplace.R;
import com.marketplace.data.model.Product;
import com.marketplace.view.base.BaseViewHolder;

import butterknife.BindView;

/**
 * Created by shima.zeinali on 5/17/2019.
 * shima.zeinalii@gmail.com
 */
public class ProductsViewHolder extends BaseViewHolder<Product, OnProductItemClickListener> {
    @BindView(R.id.tv_name) TextView tvName;
    @BindView(R.id.iv_icon) ImageView ivIcon;
    @BindView(R.id.ll_buy) LinearLayout llBuy;

    private Context context;

    public ProductsViewHolder(View itemView, final OnProductItemClickListener listener) {
        super(itemView, listener);
        context = itemView.getContext();

        if (listener != null) {
            llBuy.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(getAdapterPosition());
                }
            });
        }
    }

    @Override
    public void onBind(Product product) {
        ivIcon.setImageResource(context.getResources().getIdentifier(product.icon, "drawable", context.getPackageName()));
        tvName.setText(product.name);
    }
}
