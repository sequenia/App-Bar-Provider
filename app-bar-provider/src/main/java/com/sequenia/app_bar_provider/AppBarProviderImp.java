package com.sequenia.app_bar_provider;

import android.support.design.widget.AppBarLayout;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * Created by Ringo on 06.02.2018.
 * Методы по работе с AppBar
 * - добавление custom view в toolbar
 * - видимость кнопки home в toolbar
 * - добаваление view в CollapsingToolbarLayout
 * - добавление view в AppBar
 * - создание view с родителем AppBar
 */
public class AppBarProviderImp {

    private AppBarLayout appBar;
    private Toolbar toolbar;
    private ViewGroup collapsingContent;
    private CollapsingToolbarLayout collapsingToolbarLayout;

    public AppBarProviderImp(AppBarViews appBarViews) {
        appBar = appBarViews.getAppBar();
        toolbar = appBarViews.getToolbar();
        collapsingToolbarLayout = appBarViews.getCollapsingToolbarLayout();
        collapsingContent = appBarViews.getCollapsingContent();
        toolbar.setContentInsetStartWithNavigation(0);
    }

    public Toolbar getToolbar() {
        return toolbar;
    }

    void addViewToCollapsingView(View view) {
        collapsingContent.addView(view, collapsingContent.getChildCount());
    }

    void removeViewFromCollapsingView(View view) {
        collapsingContent.removeView(view);
    }

    View setCustomToolbarView(int layoutRes, ActionBar actionBar, LayoutInflater inflater) {
        if (actionBar != null) {
            if (layoutRes == -1) {
                actionBar.setCustomView(null);
            } else {
                ActionBar.LayoutParams params = new ActionBar.LayoutParams(
                        ActionBar.LayoutParams.MATCH_PARENT, ActionBar.LayoutParams.MATCH_PARENT);
                View customView = inflater.inflate(layoutRes, null);
                actionBar.setCustomView(customView, params);
            }
            return actionBar.getCustomView();
        }
        return null;
    }

    void setBackButtonVisibility(boolean visibility, ActionBar actionBar) {
        if (actionBar != null) {
            int displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM |
                    (visibility ? ActionBar.DISPLAY_HOME_AS_UP : 0);
            actionBar.setDisplayOptions(displayOptions);
        }
    }

    void setNeedScrollAppBar(boolean needScroll, int flags) {
        if (toolbar != null && collapsingToolbarLayout != null) {
            AppBarLayout.LayoutParams params =
                    (AppBarLayout.LayoutParams) collapsingToolbarLayout.getLayoutParams();
            params.setScrollFlags(!needScroll ?
                    0 : AppBarLayout.LayoutParams.SCROLL_FLAG_SCROLL | flags
            );
            collapsingToolbarLayout.setLayoutParams(params);
        }
    }

    void setToolbarVisibility(boolean visibility) {
        toolbar.setVisibility(visibility ? View.VISIBLE : View.GONE);
    }

    void setAppBarVisibility(boolean visibility) {
        appBar.setVisibility(visibility ? View.VISIBLE : View.GONE);
    }

    View inflateViewForAppBar(int layoutRes, LayoutInflater inflater) {
        return inflater.inflate(layoutRes, appBar, false);
    }

    void addViewToAppBar(View view) {
        appBar.addView(view, appBar.getChildCount());
    }

    void removeViewFromAppBar(View view) {
        appBar.removeView(view);
    }
}