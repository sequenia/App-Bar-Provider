package com.sequenia.app_bar_provider

import android.view.ViewGroup
import androidx.appcompat.widget.Toolbar
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.CollapsingToolbarLayout

interface AppBarViews {
    fun getAppBar(): AppBarLayout?
    fun getToolbar(): Toolbar?
    fun getCollapsingContent(): ViewGroup?
    fun getCollapsingToolbarLayout(): CollapsingToolbarLayout?
}