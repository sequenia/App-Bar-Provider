package com.sequenia.app_bar_provider;

import androidx.appcompat.widget.Toolbar;
import android.view.ViewGroup;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;

public interface AppBarViews {
    AppBarLayout getAppBar();
    Toolbar getToolbar();
    ViewGroup getCollapsingContent();
    CollapsingToolbarLayout getCollapsingToolbarLayout();
}
