package com.afs.tutrd.core.common.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch


/**
 * [BaseViewModel] : MVI BaseViewModel
 *
 * [UiState] State
 * [UiIntent] Intent
 * [SideEffect] SideEffect
 */
abstract class BaseViewModel<State: UiState, Intent: UiIntent, SideEffect: UiSideEffect>(
    initialState: State
): ViewModel() {
    protected val currentState: State
        get() = uiState.value

    private val _uiState: MutableStateFlow<State> = MutableStateFlow(initialState)
    val uiState: StateFlow<State>
        get() = _uiState.asStateFlow()

    private val _intent: MutableSharedFlow<Intent> = MutableSharedFlow()
    val intent = _intent.asSharedFlow()

    private val _sideEffect: MutableSharedFlow<SideEffect> = MutableSharedFlow()
    val sideEffect = _sideEffect.asSharedFlow()

    init {
        subscribeIntent()
    }

    /**
     * [Intent] 구독
     */
    private fun subscribeIntent() = viewModelScope.launch {
        intent.collect { handleIntent(it) }
    }

    /**
     * [Intent] 핸들러 : 모든 Intent는 여기로 들어옵니다.
     */
    protected abstract fun handleIntent(intent: Intent)

    /**
     * [State] 설정 : 상태 변경
     */
    fun reduce(reducer: State.() -> State) { _uiState.value = currentState.reducer() }

    /**
     * [Intent] 설정
     */
    fun postIntent(intent: Intent) = viewModelScope.launch { _intent.emit(intent) }

    /**
     * [SideEffect] 설정
     */
    fun postSideEffect(builder: () -> SideEffect) = viewModelScope.launch { _sideEffect.emit(builder()) }

}