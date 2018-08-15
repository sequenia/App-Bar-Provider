package com.sequenia.app_bar_provider;

public interface AppBarSettings {
    default int getFlags() {
        return 0;
    }

    default boolean isBackButtonVisible() {
        return false;
    }

    default int getCustomToolbarLayout() {
        return -1;
    }

    default boolean needScrollToolbar() {
        return false;
    }

    default boolean setToolbarVisibility() {
        return true;
    }

    default boolean setAppBarVisibility() {
        return true;
    }
}
