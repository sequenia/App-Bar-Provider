package com.sequenia.app_bar_provider

interface AppBarSettings {
    /**
     * Если передаватьь 0, то используется изображение по умолчанию из темы
     *
     * @return id ресурса изображения
     */
    fun getHomeAsUpIndicatorResourceId(): Int

    fun getFlags(): Int

    fun isBackButtonVisible(): Boolean

    fun needScrollToolbar(): Boolean

    fun setToolbarVisibility(): Boolean

    fun setAppBarVisibility(): Boolean

    fun getCustomToolbarLayout(): Int
}