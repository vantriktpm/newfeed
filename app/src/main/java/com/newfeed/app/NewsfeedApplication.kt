package com.newfeed.app

import android.app.Application
import com.newfeed.app.data.local.DatabaseInitializer
import dagger.hilt.android.HiltAndroidApp
import kotlinx.coroutines.CoroutineScope
import javax.inject.Inject

@HiltAndroidApp
class NewsfeedApplication : Application() {
    
    @Inject
    lateinit var databaseInitializer: DatabaseInitializer
    
    @Inject
    lateinit var applicationScope: CoroutineScope
    
    override fun onCreate() {
        super.onCreate()
        // Initialize database with sample data
        databaseInitializer.initializeDatabase(applicationScope)
    }
}
