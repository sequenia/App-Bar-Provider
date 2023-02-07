[![](https://jitpack.io/v/sequenia/App-Bar-Provider.svg)](https://jitpack.io/#sequenia/App-Bar-Provider)

# App-Bar-Provider

Позволяет работать с AppBar и настраивать его.

## Подключение

В `build.gradle` проекта

```
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```

В `build.gradle` приложения

```
dependencies {
    implementation 'com.github.sequenia:App-Bar-Provider:X.X.X'
}
```

## Использование

**Пример простой разметки AppBar'а:**

```  
<com.google.android.material.appbar.AppBarLayout  
	android:id="@+id/app_bar" 
	android:layout_width="match_parent"  
	android:layout_height="wrap_content">   
	
	<androidx.appcompat.widget.Toolbar
		android:id="@+id/toolbar"
		android:layout_width="match_parent"
		android:layout_height="wrap_content"
		app:navigationIcon="@drawable/ic_back" />  

</com.google.android.material.appbar.AppBarLayout>
```  

**Пример разметки AppBar'а c CollapsingToolbarLayout:**

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
				android:orientation="vertical"
				app:layout_collapseMode="parallax">  
 
					<androidx.appcompat.widget.AppCompatImageView
						 android:id="@+id/cover_image"
						 android:layout_width="match_parent"
						 android:layout_height="260dp"
						 android:layout_marginTop="24dp" />  
 
			</LinearLayout>
			
	</com.google.android.material.appbar.CollapsingToolbarLayout>
	
	<androidx.appcompat.widget.Toolbar
		android:id="@+id/toolbar"
		android:layout_width="match_parent"
		android:layout_height="wrap_content"
		app:layout_collapseMode="pin"
		app:navigationIcon="@drawable/ic_back" />  

</com.google.android.material.appbar.AppBarLayout>
```

**AppBarProvider:**

`AppBarProvider` позволяет настроить AppBar:
- показать/скрыть AppBar
- показать/скрыть toolbar
- показать/скрыть кнопку назад
- задание customView для toolbar
- изменение иконки home в toolbar
- настроить NeedScroll AppBar'а (флаги для настройки передаются)
- позволяет добавлять view в CollapsingView

Для задания этих настроек необходимо реализовать интерфейс во fragment'е, который передает настройки вида в AppBar.

**AppBarSettings:**

Реализация интерфейса по-умолчанию `DefaultAppBarSettings` имеет настройки:
- показать AppBar
- показать toolbar
- скрыть кнопку назад
- нет customView для toolbar
- не использовать NeedScroll AppBar
- нет view в CollapsingView

**Пример использования с помощью вспомогательного класса:**

```
class DefaultAppBarView(
    appBarSettings: DefaultAppBarSettings = DefaultAppBarSettings(),
    fragment: Fragment,
    view: View,
) : AppBarProvider {

    private val appBarProviderImp: AppBarProviderImp
    private val layoutInflater = fragment.layoutInflater
    private val actionBar: ActionBar

    init {
        val activity = fragment.requireActivity() as AppCompatActivity
        val appBarViews = createAppBarViews(view)

        activity.setSupportActionBar(appBarViews.getToolbar())
        actionBar = activity.supportActionBar!!

        appBarProviderImp = AppBarProviderImp(appBarViews)

        setAppBarSettings(appBarSettings)

        appBarViews.getToolbar()
            ?.setNavigationOnClickListener { fragment.findNavController().popBackStack() }
    }

    override fun getAppBarProviderImp() = appBarProviderImp

    override fun getLayoutInflater() = layoutInflater

    override fun getSupportActionBar() = actionBar

    private fun createAppBarViews(view: View): AppBarViews {
        return object : AppBarViews {
            override fun getAppBar() = view as AppBarLayout

            override fun getToolbar() = view.findViewById<Toolbar>(R.id.toolbar)

            override fun getCollapsingContent() = null

            override fun getCollapsingToolbarLayout() =
                view.findViewById<CollapsingToolbarLayout>(R.id.collapsing_toolbar_layout)

        }
    }
}
```

Класс `DefaultAppBarView` необходимо инициализировать в методе `onViewCreated` fragment'а

```
private fun initAppBar() {
	appBarView = DefaultAppBarView(fragment = this, view = binding.appBarView.root)
	val toolbarView = appBarView?.setCustomToolbarView(R.layout.custom_toolbar)
	toolbarBinding = CustomToolbarBinding.bind(toolbarView!!)
}
 ```