package com.example.pinapp.db

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert


@Dao
interface AppDao {

    @Query( "SELECT * FROM pin")
    suspend fun getRecords() : List<PinEntity>

    @Upsert
    suspend fun upsertRecord(pinEntity: PinEntity)

    @Delete
    suspend fun deleteRecord(pinEntity: PinEntity)

}