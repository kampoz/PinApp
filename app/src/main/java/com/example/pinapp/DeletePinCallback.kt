package com.example.pinapp

import com.example.pinapp.db.PinEntity

interface DeletePinCallback {
    fun onDeleteClick(pin: PinEntity)
}