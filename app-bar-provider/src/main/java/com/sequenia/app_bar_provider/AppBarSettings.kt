package com.sequenia.app_bar_provider

interface AppBarSettings {

    fun getFlags(): Int

    fun isBackButtonVisible(): Boolean

    fun needScrollToolbar(): Boolean

    fun setToolbarVisibility(): Boolean

    fun setAppBarVisibility(): Boolean

    fun getCustomToolbarLayout(): Int
}