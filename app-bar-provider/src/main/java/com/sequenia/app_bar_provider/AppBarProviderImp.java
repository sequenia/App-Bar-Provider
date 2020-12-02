package com.sequenia.app_bar_provider;

import android.content.Context;
import android.content.res.Resources;
import android.util.TypedValue;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.appbar.AppBarLayout;
import com.google.android.material.appbar.CollapsingToolbarLayout;


/**
 * Created by Ringo on 06.02.2018.
 * <p>
 * Методы по работе с AppBar
 * - добавление custom view в @{@link Toolbar}
 * - видимость кнопки home в @{@link Toolbar}
 * - изменение иконки home в @{@link Toolbar}
 * - добаваление view в @{@link CollapsingToolbarLayout}
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

    void addViewToCollapsingView(View view) {
        collapsingContent.addView(view, collapsingContent.getChildCount());
    }

    void removeViewFromCollapsingView(View view) {
        collapsingContent.removeView(view);
    }

    View setCustomToolbarView(int layoutRes, ActionBar actionBar, LayoutInflater inflater) {
        if (actionBar == null) {
            return null;
        }

        if (layoutRes == -1) {
            actionBar.setCustomView(null);
        } else {
            ActionBar.LayoutParams params = new ActionBar.LayoutParams(
                    ActionBar.LayoutParams.MATCH_PARENT,
                    ActionBar.LayoutParams.MATCH_PARENT
            );

            View customView = inflater.inflate(layoutRes, null);
            actionBar.setCustomView(customView, params);
        }

        return actionBar.getCustomView();
    }

    void setBackButtonVisibility(boolean visibility, ActionBar actionBar) {
        if (actionBar == null) {
            return;
        }

        int displayOptions = ActionBar.DISPLAY_SHOW_CUSTOM |
                (visibility ? ActionBar.DISPLAY_HOME_AS_UP : 0);
        actionBar.setDisplayOptions(displayOptions);
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

    void setHomeAsUpIndicator(ActionBar actionBar, Integer drawableRes) {
        if (actionBar == null || appBar == null) {
            return;
        }

        if (drawableRes == null) {
            drawableRes = getDefaultHomeAsUpIndicator();
        }

        // по каким-то причинам не удалось достать иконку по умолчанию
        if (drawableRes == null) {
            return;
        }

        actionBar.setHomeAsUpIndicator(drawableRes);
    }

    private Integer getDefaultHomeAsUpIndicator() {
        Context context = appBar.getContext();

        if (context == null) {
            return null;
        }

        Resources.Theme theme = context.getTheme();

        if (theme == null) {
            return null;
        }

        TypedValue typedValue = new TypedValue();
        theme.resolveAttribute(android.R.attr.homeAsUpIndicator, typedValue, true);
        return typedValue.resourceId;
    }
}