package com.example.testtask.presentation.utils

import android.provider.ContactsContract

sealed class DataState {
    object NothingToShow: DataState()
    object Loading: DataState()
    object Success: DataState()
}