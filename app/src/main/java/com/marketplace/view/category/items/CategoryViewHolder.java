package com.marketplace.view.category.items;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.marketplace.R;
import com.marketplace.data.model.Category;
import com.marketplace.view.base.BaseViewHolder;

import butterknife.BindView;

/**
 * Created by shima.zeinali on 5/16/2019.
 * shima.zeinalii@gmail.com
 */
public class CategoryViewHolder extends BaseViewHolder<Category, OnCategoryItemClickListener> {
    @BindView(R.id.tv_name) TextView tvName;
    @BindView(R.id.iv_icon) ImageView ivIcon;
    private Context context;

    public CategoryViewHolder(View itemView, final OnCategoryItemClickListener listener) {
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
    public void onBind(Category category) {
        ivIcon.setImageResource(context.getResources().getIdentifier(category.icon, "drawable", context.getPackageName()));
        tvName.setText(category.name);
    }
}
