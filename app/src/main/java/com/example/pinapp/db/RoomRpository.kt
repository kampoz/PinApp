package com.example.pinapp.db

import kotlinx.coroutines.runBlocking
import javax.inject.Inject

class RoomRpository @Inject constructor(private val appDao: AppDao) {

    suspend fun getRecords() : List<PinEntity> {
        return appDao.getRecords()
    }

    fun upsertRecords(pinEntity: PinEntity) {
        runBlocking {
            appDao.upsertRecord(pinEntity)
        }
    }

    fun deleteRecord(pinEntity: PinEntity) {
        runBlocking {
            appDao.deleteRecord(pinEntity)
        }
    }
}