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
        createAndSetCustomToolbarView(settings.getCustomToolbarLayout())
        setNeedScrollAppBar(settings.needScrollToolbar(), settings.getFlags())
        setToolbarVisibility(settings.setToolbarVisibility())
        setAppBarVisibility(settings.setAppBarVisibility())
    }

    fun inflateViewForAppBar(layoutResourceId: Int): View? {
        return getAppBarProviderImp().inflateViewForAppBar(layoutResourceId, getLayoutInflater())
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

    fun createAndSetCustomToolbarView(layoutResource: Int): View? {
        return getAppBarProviderImp().createAndSetCustomToolbarView(
            layoutResource,
            getSupportActionBar(), getLayoutInflater()
        )
    }

    fun addViewToCollapsingView(view: View) {
        getAppBarProviderImp().addViewToCollapsingView(view)
    }

    fun removeViewFromCollapsingView(view: View?) {
        getAppBarProviderImp().removeViewFromCollapsingView(view)
    }

    fun setToolbarTitle(title: String) {
        getAppBarProviderImp().setToolbarTitle(title)
    }

    fun setToolbarSubtitle(subtitle: String) {
        getAppBarProviderImp().setToolbarSubtitle(subtitle)
    }
}