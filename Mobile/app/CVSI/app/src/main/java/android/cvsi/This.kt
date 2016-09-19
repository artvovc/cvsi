package com.dsdmsa.weather

import android.app.Application
import com.dsdmsa.weather.controllers.CacheController
import com.dsdmsa.weather.managers.QueryManager
import io.realm.Realm
import io.realm.RealmConfiguration
import org.greenrobot.eventbus.EventBus
import kotlin.properties.Delegates

class This : Application() {

    companion object {
        var cacheController: CacheController? = null
        var queryManager: QueryManager = QueryManager()
        var bus = EventBus()
        private var realmConfig: RealmConfiguration by Delegates.notNull()
        var DB: Realm by Delegates.notNull()
        fun getDB() {
            DB = Realm.getInstance(realmConfig)
        }
    }

    override fun onCreate() {
        super.onCreate()
        cacheController = CacheController(applicationContext)
        realmConfig = RealmConfiguration.Builder(this).deleteRealmIfMigrationNeeded().build()
        getDB()
    }

}
