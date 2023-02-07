package com.sequenia.app_bar_provider

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.ActionBar
import androidx.appcompat.widget.Toolbar
import androidx.core.view.isVisible
import androidx.core.view.updateLayoutParams
import com.google.android.material.appbar.AppBarLayout
import com.google.android.material.appbar.CollapsingToolbarLayout

open class AppBarProviderImp(
    appBarViews: AppBarViews,
) {
    private val appBar: AppBarLayout?
    private var toolbar: Toolbar?
    private var collapsingContent: ViewGroup? = null
    private var collapsingToolbarLayout: CollapsingToolbarLayout? = null

    init {
        appBar = appBarViews.getAppBar()
        toolbar = appBarViews.getToolbar()
        collapsingToolbarLayout = appBarViews.getCollapsingToolbarLayout()
        collapsingContent = appBarViews.getCollapsingContent()
        toolbar?.contentInsetStartWithNavigation = 0
    }

    fun addViewToCollapsingView(view: View) {
        collapsingContent?.let { it.addView(view, it.childCount) }
    }

    fun removeViewFromCollapsingView(view: View?) {
        collapsingContent?.removeView(view)
    }

    fun setCustomToolbarView(
        layoutRes: Int,
        actionBar: ActionBar,
        inflater: LayoutInflater,
    ): View? {
        if (layoutRes == -1) {
            actionBar.customView = null
            return null
        }

        val params = ActionBar.LayoutParams(
            ActionBar.LayoutParams.MATCH_PARENT,
            ActionBar.LayoutParams.MATCH_PARENT
        )

        val customView = inflater.inflate(layoutRes, null)
        actionBar.setCustomView(customView, params)

        return actionBar.customView
    }

    fun setBackButtonVisibility(visibility: Boolean, actionBar: ActionBar) {
        val displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM or
                if (visibility) ActionBar.DISPLAY_HOME_AS_UP else 0
        actionBar.displayOptions = displayOptions
    }

    fun setNeedScrollAppBar(needScroll: Boolean, flags: Int) {
        collapsingToolbarLayout?.updateLayoutParams<AppBarLayout.LayoutParams> {
            this.scrollFlags =
                if (!needScroll) 0 else AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL or flags
        }
    }

    fun setToolbarVisibility(isVisible: Boolean) {
        toolbar?.isVisible = isVisible
    }

    fun setAppBarVisibility(isVisible: Boolean) {
        appBar?.isVisible = isVisible
    }

    fun inflateViewForAppBar(layoutRes: Int, inflater: LayoutInflater): View? {
        return inflater.inflate(layoutRes, appBar, false)
    }

    fun addViewToAppBar(view: View) {
        appBar?.let { it.addView(view, it.childCount) }
    }

    fun removeViewFromAppBar(view: View) {
        appBar?.removeView(view)
    }

    fun setHomeAsUpIndicator(actionBar: ActionBar, drawableRes: Int) {
        actionBar.setHomeAsUpIndicator(drawableRes)
    }

    fun setToolbarTitle(title: String) {
        toolbar?.title = title
    }
}