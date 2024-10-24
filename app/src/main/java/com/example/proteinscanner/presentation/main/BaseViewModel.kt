package com.example.proteinscanner.presentation.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.proteinscanner.domain.viewstate.ViewEvent
import com.example.proteinscanner.domain.viewstate.ViewState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch

abstract class BaseViewModel<State : ViewState, Event : ViewEvent> : ViewModel() {

    private val initialState: State by lazy { createInitialState() }
    abstract fun createInitialState(): State

    abstract fun onTriggerEvent(event: Event)

    private val _uiState: MutableStateFlow<State> = MutableStateFlow(initialState)
    val uiState: StateFlow<State> = _uiState

    private val _uiEvent: MutableSharedFlow<Event> = MutableSharedFlow()
    val uiEvent = _uiEvent.asSharedFlow()

    val currentState: State get() = uiState.value

    protected fun setState(reduce: State.() -> State) {
        val newState = currentState.reduce()
        _uiState.value = newState
    }

    protected fun setEvent(event: Event) {
        viewModelScope.launch { _uiEvent.emit(event) }
    }

    protected suspend fun <T> call(
        callFlow: Flow<T>,
        completionHandler: (collect: T) -> Unit = {}
    ) {
        callFlow
            .catch { }
            .collect {
                completionHandler.invoke(it)
            }
    }

}