package com.marketplace.view.category;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.marketplace.R;
import com.marketplace.data.model.Category;
import com.marketplace.view.base.BaseFragment;
import com.marketplace.view.category.items.OnCategoryItemClickListener;
import com.marketplace.view.products.ProductFragment;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by shima.zeinali on 5/16/2019.
 * shima.zeinalii@gmail.com
 */
public class CategoryFragment extends BaseFragment<CategoryViewModel> {
    public OnCategoryItemClickListener listener = new OnCategoryItemClickListener() {
        @Override
        public void onItemClick(int position) {
            getBaseActivity().addFragment(ProductFragment.newInstance());
        }
    };
    @Inject
    CategoryViewModel viewModel;
    @Inject
    CategoryAdapter adapter;

    @BindView(R.id.rv_category)
    RecyclerView recyclerView;

    public static CategoryFragment newInstance() {
        Bundle args = new Bundle();
        CategoryFragment fragment = new CategoryFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getBaseActivity().setDisplayHomeAsUp(false);
        subscribeToLiveData();
        setupList(recyclerView);
    }

    private void subscribeToLiveData() {
        viewModel.getCategoryListLiveData().observe(this, new Observer<List<Category>>() {
            @Override
            public void onChanged(@Nullable List<Category> items) {
                adapter.clear();
                adapter.setItems(items);
            }
        });
    }

    private void setupList(RecyclerView rvCategory) {
        rvCategory.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        rvCategory.setAdapter(adapter);
    }

    @Override
    public CategoryViewModel getViewModel() {
        return viewModel;
    }

    @Override
    public int getActionMenu() {
        return 0;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_category;
    }

    @Override
    protected int getTitleId() {
        return R.string.title_category;
    }

}
