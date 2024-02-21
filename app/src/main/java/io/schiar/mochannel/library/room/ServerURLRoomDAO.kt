package io.schiar.mochannel.library.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface ServerURLRoomDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(serverURLEntity: ServerURLEntity): Long

    @Update
    suspend fun update(serverURLEntity: ServerURLEntity)

    @Query("SELECT * FROM ServerURL LIMIT 1")
    fun select(): Flow<ServerURLEntity?>
}