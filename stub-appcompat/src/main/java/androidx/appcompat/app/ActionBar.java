package androidx.appcompat.app;

import android.view.View;

public abstract class ActionBar {
    public static final int DISPLAY_SHOW_CUSTOM = android.app.ActionBar.DISPLAY_SHOW_CUSTOM;
    public static final int DISPLAY_HOME_AS_UP = android.app.ActionBar.DISPLAY_HOME_AS_UP;

    public abstract void setCustomView(View view);
    public abstract void setCustomView(View view, LayoutParams layoutParams);
    public abstract View getCustomView();
    public abstract void setDisplayOptions(int options);

    public static class LayoutParams {
        public static final int MATCH_PARENT = -1;

        public LayoutParams(int width, int height) {
            throw new RuntimeException("Stub!");
        }
    }
}