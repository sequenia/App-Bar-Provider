package com.sequenia.app_bar_provider

class DefaultAppBarSettings : AppBarSettings {

    override fun getFlags() = 0

    override fun isBackButtonVisible() = false

    override fun needScrollToolbar() = false

    override fun setToolbarVisibility() = true

    override fun setAppBarVisibility() = true

    override fun getCustomToolbarLayout() = -1
}