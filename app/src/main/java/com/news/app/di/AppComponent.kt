package com.news.app.di

import android.app.Application
import com.news.app.MyApplication
import com.news.app.di.list.JsonAdapterModule
import com.news.app.di.vm.ViewModelModule
import dagger.BindsInstance
import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import javax.inject.Singleton

@Singleton
@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        AppModule::class,
        JsonAdapterModule::class,
        ViewModelModule::class,
        ActivityBindingModule::class]
)
interface AppComponent : AndroidInjector<MyApplication> {
    @Component.Factory
    interface Factory {
        fun create(@BindsInstance application: Application): AppComponent
    }
}