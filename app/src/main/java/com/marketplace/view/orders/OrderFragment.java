package com.marketplace.view.orders;

import android.arch.lifecycle.Observer;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.marketplace.R;
import com.marketplace.data.model.Order;
import com.marketplace.view.base.BaseFragment;
import com.marketplace.view.orders.items.OnOrderItemClickListener;

import java.util.List;

import javax.inject.Inject;

import butterknife.BindView;

/**
 * Created by shima.zeinali on 5/20/2019.
 * shima.zeinalii@gmail.com
 */
public class OrderFragment extends BaseFragment<OrderViewModel> {
    final static int DELAY_MILLIS = 5000;
    public OnOrderItemClickListener listener = new OnOrderItemClickListener() {
        @Override
        public void onItemClick(int position) {
        }
    };
    @Inject
    OrderAdapter adapter;
    @Inject
    OrderViewModel viewModel;
    @BindView(R.id.rv_order)
    RecyclerView recyclerView;
    private Handler handler;
    private Runnable runnable;

    public static OrderFragment newInstance() {
        Bundle args = new Bundle();
        OrderFragment fragment = new OrderFragment();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        getBaseActivity().setDisplayHomeAsUp(false);
        subscribeToLiveData();
        setupList(recyclerView);
    }

    private void subscribeToLiveData() {
        viewModel.getOrderListLiveData().observe(this, new Observer<List<Order>>() {
            @Override
            public void onChanged(@Nullable List<Order> items) {
                adapter.clear();
                adapter.setItems(items);
            }
        });
    }

    private void startListUpdate() {
        handler = new Handler();
        runnable = new Runnable() {
            @Override
            public void run() {
                viewModel.updateList();
                handler.postDelayed(this, DELAY_MILLIS);
            }
        };
        handler.post(runnable);
    }

    @Override
    public void onResume() {
        super.onResume();
        startListUpdate();
    }

    @Override
    public void onPause() {
        handler.removeCallbacks(runnable);
        super.onPause();
    }

    private void setupList(RecyclerView rvOrders) {
        rvOrders.setLayoutManager(new LinearLayoutManager(getActivity(), LinearLayoutManager.VERTICAL, false));
        rvOrders.setAdapter(adapter);
    }

    @Override
    public OrderViewModel getViewModel() {
        return viewModel;
    }

    @Override
    public int getActionMenu() {
        return 0;
    }

    @Override
    public int getLayoutId() {
        return R.layout.fragment_order;
    }

    @Override
    protected int getTitleId() {
        return R.string.title_order;
    }
}
