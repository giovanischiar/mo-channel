package io.schiar.mochannel.model.datasource.settings.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update

@Dao
interface ServerURLDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(serverURLEntity: ServerURLEntity): Long

    @Update
    fun update(serverURLEntity: ServerURLEntity)

    @Query("SELECT * FROM ServerURL LIMIT 1")
    fun select(): ServerURLEntity?
}