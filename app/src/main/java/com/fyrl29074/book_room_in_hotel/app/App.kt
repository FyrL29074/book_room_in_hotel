package com.fyrl29074.book_room_in_hotel.app

import android.app.Application
import com.fyrl29074.book_room_in_hotel.di.appModule
import com.fyrl29074.book_room_in_hotel.di.dataModule
import com.fyrl29074.book_room_in_hotel.di.domainModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class App: Application() {
    override fun onCreate() {
        super.onCreate()

        val modules = listOf(
            appModule,
            domainModule,
            dataModule,
        )
        startKoin {
            androidContext(this@App)
            modules(modules)
        }
    }
}
