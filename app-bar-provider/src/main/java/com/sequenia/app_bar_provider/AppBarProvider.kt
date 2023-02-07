package com.sequenia.app_bar_provider

import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.ActionBar

interface AppBarProvider {

    fun getAppBarProviderImp(): AppBarProviderImp

    fun getLayoutInflater(): LayoutInflater

    fun getSupportActionBar(): ActionBar

    fun setAppBarSettings(settings: AppBarSettings?) {
        if (settings == null) return
        setBackButtonVisibility(settings.isBackButtonVisible())
        setCustomToolbarView(settings.getCustomToolbarLayout())
        setNeedScrollAppBar(settings.needScrollToolbar(), settings.getFlags())
        setToolbarVisibility(settings.isToolbarVisibility())
        setAppBarVisibility(settings.isAppBarVisibility())
        setHomeAsUpIndicator(settings.getHomeAsUpIndicatorRes())
    }

    fun inflateViewForAppBar(layoutRes: Int): View? {
        return getAppBarProviderImp().inflateViewForAppBar(layoutRes, getLayoutInflater())
    }

    fun addViewToAppBar(view: View) {
        getAppBarProviderImp().addViewToAppBar(view)
    }

    fun removeViewFromAppBar(view: View) {
        getAppBarProviderImp().removeViewFromAppBar(view)
    }

    fun setAppBarVisibility(visibility: Boolean) {
        getAppBarProviderImp().setAppBarVisibility(visibility)
    }

    fun setToolbarVisibility(visibility: Boolean) {
        getAppBarProviderImp().setToolbarVisibility(visibility)
    }

    fun setNeedScrollAppBar(needScroll: Boolean, flags: Int) {
        getAppBarProviderImp().setNeedScrollAppBar(needScroll, flags)
    }

    fun setBackButtonVisibility(visibility: Boolean) {
        getAppBarProviderImp().setBackButtonVisibility(visibility, getSupportActionBar())
    }

    fun setCustomToolbarView(layoutRes: Int): View? {
        return getAppBarProviderImp().setCustomToolbarView(
            layoutRes,
            getSupportActionBar(), getLayoutInflater()
        )
    }

    fun addViewToCollapsingView(view: View) {
        getAppBarProviderImp().addViewToCollapsingView(view)
    }

    fun removeViewFromCollapsingView(view: View?) {
        getAppBarProviderImp().removeViewFromCollapsingView(view)
    }

    fun setHomeAsUpIndicator(drawableRes: Int) {
        getAppBarProviderImp().setHomeAsUpIndicator(getSupportActionBar(), drawableRes)
    }

    fun setToolbarTitle(title: String) {
        getAppBarProviderImp().setToolbarTitle(title)
    }
}