package com.sequenia.app_bar_provider;

import android.view.LayoutInflater;
import android.view.View;

import androidx.appcompat.app.ActionBar;

/**
 * Created by Ringo on 06.02.2018.
 * Интерфейс для настройки AppBar
 */
public interface AppBarProvider {
    AppBarProviderImp getAppBarProviderImp();

    LayoutInflater getLayoutInflater();

    ActionBar getSupportActionBar();

    default void setAppBarSettings(AppBarSettings settings) {
        if (settings == null) {
            return;
        }

        this.setBackButtonVisibility(settings.isBackButtonVisible());
        this.setCustomToolbarView(settings.getCustomToolbarLayout());
        this.setNeedScrollAppBar(settings.needScrollToolbar(), settings.getFlags());
        this.setToolbarVisibility(settings.setToolbarVisibility());
        this.setAppBarVisibility(settings.setAppBarVisibility());
        this.setHomeAsUpIndicator(settings.getHomeAsUpIndicatorRes());
    }

    default View inflateViewForAppBar(int layoutRes) {
        return getAppBarProviderImp().inflateViewForAppBar(layoutRes, getLayoutInflater());
    }

    default void addViewToAppBar(View view) {
        getAppBarProviderImp().addViewToAppBar(view);
    }

    default void removeViewFromAppBar(View view) {
        getAppBarProviderImp().removeViewFromAppBar(view);
    }

    default void setAppBarVisibility(boolean visibility) {
        getAppBarProviderImp().setAppBarVisibility(visibility);
    }

    default void setToolbarVisibility(boolean visibility) {
        getAppBarProviderImp().setToolbarVisibility(visibility);
    }

    default void setNeedScrollAppBar(boolean needScroll, int flags) {
        getAppBarProviderImp().setNeedScrollAppBar(needScroll, flags);
    }

    default void setBackButtonVisibility(boolean visibility) {
        getAppBarProviderImp().setBackButtonVisibility(visibility, getSupportActionBar());
    }

    default View setCustomToolbarView(int layoutRes) {
        return getAppBarProviderImp().setCustomToolbarView(layoutRes,
                getSupportActionBar(), getLayoutInflater());
    }

    default void addViewToCollapsingView(View view) {
        getAppBarProviderImp().addViewToCollapsingView(view);
    }

    default void removeViewFromCollapsingView(View view) {
        getAppBarProviderImp().removeViewFromCollapsingView(view);
    }

    default void setHomeAsUpIndicator(Integer drawableRes) {
        getAppBarProviderImp().setHomeAsUpIndicator(getSupportActionBar(), drawableRes);
    }
}