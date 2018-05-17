package com.sequenia.app_bar_provider;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.widget.Toolbar;
import android.view.ViewGroup;

public interface AppBarViews {
    AppBarLayout getAppBar();
    Toolbar getToolbar();
    ViewGroup getCollapsingContent();
    CollapsingToolbarLayout getCollapsingToolbarLayout();
}
