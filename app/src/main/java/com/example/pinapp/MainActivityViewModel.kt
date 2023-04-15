package com.example.pinapp


import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.pinapp.db.PinEntity
import com.example.pinapp.db.RoomRpository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.runBlocking
import javax.inject.Inject


@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val repository: RoomRpository,
    private val pinValidator: PinValidator,
    private val pinGenerator: PinGenerator
) : ViewModel() {
    val pinData: MutableLiveData<List<PinEntity>> = MutableLiveData()
    var pinToDelete: MutableLiveData<PinEntity> = MutableLiveData()
    var pinToAdd: String = ""

    init {
        loadRecords()
    }

    fun loadRecords() {
        runBlocking {
            val list = repository.getRecords()
            pinData.postValue(list)
        }
    }

    fun upsertRecords(pinEntity: PinEntity) {
        repository.upsertRecords(pinEntity)
        loadRecords()
    }

    fun saveNewPin(name: String) {
        val pinEntity = PinEntity(
            name = name,
            code = pinToAdd
        )
        upsertRecords(pinEntity)
    }

    fun generatePin() {
        val newPin = pinGenerator.generate()
        if (pinValidator.isValid(newPin)) {
            pinToAdd = newPin
        }
    }

    fun deleteRecord(pinEntity: PinEntity) {
        repository.deleteRecord(pinEntity)
        loadRecords()
    }
}