package com.example.testtask.presentation.view_models

import androidx.lifecycle.Lifecycle
import androidx.lifecycle.OnLifecycleEvent
import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable

abstract class ParentViewModel : ViewModel() {

    protected var disposables = CompositeDisposable()

    override fun onCleared() {
        disposables.clear()
        super.onCleared()
    }

    @OnLifecycleEvent(Lifecycle.Event.ON_DESTROY)
    fun removeDisposables() {
        disposables.clear()
    }
}