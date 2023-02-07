package com.sequenia.app_bar_provider

@JvmDefaultWithoutCompatibility
interface AppBarSettings {
    /**
     * Если передаватьь 0, то используется изображение по умолчанию из темы
     *
     * @return id ресурса изображения
     */
    fun getHomeAsUpIndicatorRes(): Int = 0

    fun getFlags(): Int = 0

    fun isBackButtonVisible(): Boolean = false

    fun needScrollToolbar(): Boolean = false

    fun setToolbarVisibility(): Boolean = true

    fun setAppBarVisibility(): Boolean = true

    fun getCustomToolbarLayout(): Int = -1
}