package io.schiar.mochannel.library.room

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "ServerURL")
data class ServerURLEntity(
    @PrimaryKey(autoGenerate = false)
    val id: Long = 1,
    val prefix: String,
    val url: String,
    val port: String
)