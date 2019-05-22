package com.marketplace.view.home;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomNavigationView;
import android.view.MenuItem;

import com.marketplace.R;
import com.marketplace.view.base.BaseActivity;
import com.marketplace.view.base.BaseFragment;
import com.marketplace.view.category.CategoryFragment;
import com.marketplace.view.orders.OrderFragment;

import java.util.Objects;

import javax.inject.Inject;

import butterknife.BindView;

public class HomeActivity extends BaseActivity<HomeViewModel> {
    @Inject
    ViewModelProvider.Factory viewModelFactory;
    @Nullable
    @BindView(R.id.navigation)
    BottomNavigationView navigation;
    private HomeViewModel viewModel;

    @Override
    public int getLayoutId() {
        return R.layout.activity_home;
    }

    @Override
    public HomeViewModel getViewModel() {
        viewModel = ViewModelProviders.of(this, viewModelFactory).
                get(HomeViewModel.class);
        return viewModel;
    }

    @Override
    public int getActionMenu() {
        return 0;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupNavigation();
        subscribeToTabStatusLiveData();
    }

    @Override
    public void onBackPressed() {
        if (getSupportFragmentManager().getBackStackEntryCount() == 1)
            finish();
        else {
            BaseFragment fragment = (BaseFragment) getSupportFragmentManager().findFragmentById(R.id.f_container);
            Objects.requireNonNull(fragment).onBackPress();
            if (fragment instanceof CategoryFragment || fragment instanceof OrderFragment) {
                finish();
            } else {
                super.onBackPressed();
            }
        }
    }

    private void setupNavigation() {
        navigation.setOnNavigationItemSelectedListener(new NavigationItemSelected());
        navigation.setSelectedItemId(R.id.navigation_category);
    }

    private void subscribeToTabStatusLiveData() {
        viewModel.getTabStatus().observe(this, new Observer<Boolean>() {
            @Override
            public void onChanged(@Nullable Boolean items) {
                navigation.setSelectedItemId(R.id.navigation_orders);
            }
        });
    }

    private class NavigationItemSelected implements BottomNavigationView.OnNavigationItemSelectedListener {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_category:
                    replaceFragment(CategoryFragment.newInstance());
                    return true;
                case R.id.navigation_orders:
                    replaceFragment(OrderFragment.newInstance());
                    return true;
            }
            return false;
        }
    }
}
