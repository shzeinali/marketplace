package com.marketplace.view.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.Menu;

import com.marketplace.R;

import butterknife.ButterKnife;
import dagger.android.support.DaggerAppCompatActivity;

/**
 * Created by shima.zeinali on 5/16/2019.
 * shima.zeinalii@gmail.com
 */
public abstract class BaseActivity<T extends BaseViewModel> extends DaggerAppCompatActivity
        implements BaseFragment.Callback {
    private T viewModel;

    public abstract
    @LayoutRes
    int getLayoutId();

    public abstract T getViewModel();

    public abstract int getActionMenu();

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        this.viewModel = viewModel == null ? getViewModel() : viewModel;
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        if (getActionMenu() != 0)
            getMenuInflater().inflate(getActionMenu(), menu);
        return true;
    }

    public void setDisplayHomeAsUp(boolean isEnable) {
        if (getSupportActionBar() == null)
            return;
        getSupportActionBar().setDisplayHomeAsUpEnabled(isEnable);
    }

    public void addFragment(Fragment fragment) {
        String tag = fragment.getClass().getName();
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.add(R.id.f_container, fragment, tag);
        transaction.addToBackStack(tag);
        transaction.commit();
    }

    protected void replaceFragment(Fragment fragment) {
        String tag = fragment.getClass().getName();
        FragmentManager manager = getSupportFragmentManager();
        boolean fragmentPopped = manager.popBackStackImmediate(tag, 0);

        if (!fragmentPopped && manager.findFragmentByTag(tag) == null) {
            FragmentTransaction ft = manager.beginTransaction();
            ft.replace(R.id.f_container, fragment, tag);
            ft.addToBackStack(tag);
            ft.commit();
        }
    }

    @Override
    public void onFragmentAttached(String title) {
        getSupportActionBar().setTitle(title);
    }

    @Override
    public void onFragmentDetached(String tag) {

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        //TODO :D I know!
        Fragment fragment = getSupportFragmentManager().findFragmentById(R.id.f_container);
        fragment.onActivityResult(requestCode, resultCode, data);
    }
}
