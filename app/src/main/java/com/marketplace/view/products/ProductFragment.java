package com.marketplace.view.products;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.marketplace.R;
import com.marketplace.data.model.Product;
import com.marketplace.view.base.BaseFragment;
import com.marketplace.view.map.MapFragment;
import com.marketplace.view.products.items.OnProductItemClickListener;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

import static com.marketplace.util.AppConstants.PRODUCT_ID;

/**
 * Created by shima.zeinali on 5/17/2019.
 * shima.zeinalii@gmail.com
 */
public class ProductFragment extends BaseFragment<ProductViewModel> {

    public OnProductItemClickListener listener = new OnProductItemClickListener() {
        @Override
        public void onItemClick(int position) {
            Bundle args = new Bundle();
            args.putInt(PRODUCT_ID, position);
            getBaseActivity().addFragment(MapFragment.newInstance(args));
        }
    };
    @Inject
    ProductViewModel viewModel;
    @Inject
    ProductAdapter adapter;
    @BindView(R.id.rv_product)
    RecyclerView recyclerView;

    public static ProductFragment newInstance() {
        Bundle args = new Bundle();
        ProductFragment fragment = new ProductFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getBaseActivity().setDisplayHomeAsUp(true);
        subscribeToLiveData();
        setupList(recyclerView);

    }

    private void subscribeToLiveData() {
        viewModel.getProductListLiveData().observe(this, new Observer<List<Product>>() {
            @Override
            public void onChanged(@Nullable List<Product> items) {
                adapter.clear();
                adapter.setItems(items);
            }
        });
    }

    private void setupList(RecyclerView rvProducts) {
        rvProducts.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        rvProducts.setAdapter(adapter);
    }

    @Override
    public ProductViewModel getViewModel() {
        return viewModel;
    }

    @Override
    public int getActionMenu() {
        return 0;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_product;
    }

    @Override
    protected int getTitleId() {
        return R.string.title_product;
    }
}
