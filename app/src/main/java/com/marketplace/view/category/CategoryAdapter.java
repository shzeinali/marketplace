package com.marketplace.view.category;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.marketplace.R;
import com.marketplace.data.model.Category;
import com.marketplace.view.base.BaseRecyclerViewAdapter;
import com.marketplace.view.category.items.CategoryViewHolder;
import com.marketplace.view.category.items.OnCategoryItemClickListener;

/**
 * Created by shima.zeinali on 5/16/2019.
 * shima.zeinalii@gmail.com
 */
public class CategoryAdapter extends BaseRecyclerViewAdapter<Category, OnCategoryItemClickListener, CategoryViewHolder> {

    public CategoryAdapter(Context context, OnCategoryItemClickListener listener) {
        super(context, listener);
    }

    @NonNull
    @Override
    public CategoryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_category, parent, false);
        return new CategoryViewHolder(view, getListener());
    }
}
