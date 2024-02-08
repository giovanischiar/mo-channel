package io.schiar.mochannel

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import io.schiar.mochannel.model.datasource.settings.database.ServerURLDAO
import io.schiar.mochannel.model.datasource.settings.database.ServerURLEntity

@Database(
    entities = [ServerURLEntity::class],
    version = 1,
    exportSchema = false
)
abstract class MoChannelDatabase : RoomDatabase() {
    abstract fun serverURLDAO(): ServerURLDAO

    companion object {
        @Volatile
        private var Instance: MoChannelDatabase? = null

        fun getDatabase(context: Context): MoChannelDatabase {
            return Instance ?: synchronized(this) {
                Room.databaseBuilder(
                    context = context,
                    klass = MoChannelDatabase::class.java,
                    name = "mo_channel_database"
                ).fallbackToDestructiveMigration().build().also { Instance = it }
            }
        }
    }
}