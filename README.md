[![](https://jitpack.io/v/sequenia/App-Bar-Provider.svg)](https://jitpack.io/#sequenia/App-Bar-Provider)

# App-Bar-Provider

Позволяет работать с AppBar и настраивать его.

## Подключение

```
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}

dependencies {
    implementation 'com.github.sequenia:App-Bar-Provider:vX.X.X'
}
```

## Использование

Пример разметки AppBar'а

```
<com.google.android.material.appbar.AppBarLayout
        android:id="@+id/app_bar"
        android:layout_width="match_parent"
        android:layout_height="wrap_content">

    <com.google.android.material.appbar.CollapsingToolbarLayout
        android:id="@+id/collapsing_toolbar"
        android:layout_width="match_parent"
        android:layout_height="wrap_content">

        <LinearLayout
            android:id="@+id/collapsing_content"
            android:layout_width="match_parent"
            android:layout_height="wrap_content"
            android:orientation="vertical">

            <androidx.appcompat.widget.Toolbar
                android:id="@+id/toolbar"
                android:layout_width="match_parent"
                android:layout_height="wrap_content" />

        </LinearLayout>

    </com.google.android.material.appbar.CollapsingToolbarLayout>

</com.google.android.material.appbar.AppBarLayout>
```

В activity необходимо реализовать два интерфейса.

```
public interface AppBarViews {
    AppBarLayout getAppBar();
    Toolbar getToolbar();
    ViewGroup getCollapsingContent();
    CollapsingToolbarLayout getCollapsingToolbarLayout();
}
```

Интерфейс предоставляет методы для обращения к view AppBar'а.
Реализацию интерфейса необходимо передать в констуктор AppBarProviderImp. 

```
appBarProviderImp = new AppBarProviderImp(AppBarSettings);
```

```
AppBarProvider
```

Позволяет насроить AppBar:
- показать/скрыть AppBar
- показать/скрыть toolbar
- показать/скрыть кнопку назад
- задание customView для toolbar
- изменение иконки home в toolbar
- настроить NeedScroll AppBar'а (флаги для настройки передаются)
- позволяет добавлять view в CollapsingView

Для задания этих настроек необходимо реализовать интерфейс во fragment'е, который передает настройки вида в AppBar.

```
AppBarSettings
```
 
Интерфейс имеет настройки поумолчанию:
- показать AppBar
- показать toolbar
- скрыть кнопку назад
- нет customView для toolbar
- не использовать NeedScroll AppBar
- нет view в CollapsingView

Задание toolbar

```
setSupportActionBar(appBarProviderImp.getToolbar());
```

Получение доступа к интерфейсу AppBar'а из fragment'а

```
protected AppBarProvider appBarProvider;

@Override
public void onAttach(Context context) {
    super.onAttach(context);
    if (context instanceof AppBarProvider) {
        appBarProvider = (AppBarProvider) context;
    } else {
        throw new RuntimeException("Activity must implement AppBarProvider");
    }
}
```

Пример задания заголовка экрана
```
@Override
public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
    super.onViewCreated(view, savedInstanceState);
    appBarProvider.setAppBarSettings(this);
    ((TextView) appBarProvider.setCustomToolbarView(R.layout.view_tollbar_title))
                    .setText(getTitle());
}
```