package com.example.zubuttest.sys.di.modules;

import android.content.Context;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;

import dagger.Module;
import dagger.Provides;

@Module
public class ContextModule {
    private FragmentActivity fragmentActivity;

    private Fragment fragment;

    private Context context;

    public ContextModule(FragmentActivity fragmentActivity) {
        this.fragmentActivity = fragmentActivity;
    }

    public ContextModule(Fragment fragment) {
        this.fragment = fragment;
    }

    public ContextModule(Context context) {
        this.context = context;
    }

    @Provides
    public Fragment provideFragment() {
        return this.fragment;
    }

    @Provides
    public Context provideContext() {
        Context mContext = this.context;

        if (mContext == null && fragmentActivity != null)
            mContext = fragmentActivity.getBaseContext();

        if (fragment != null)
            mContext = fragment.getContext();

        return mContext;
    }

}
