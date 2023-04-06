package com.example.myapplication.scans

import androidx.compose.runtime.State
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.toMutableStateList
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import javax.inject.Inject
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

class ScansViewModel @Inject constructor(
    private val getScansUseCase: ScansUseCase
) : ViewModel() {

    private val _state = mutableStateOf(ScansState())
    val state: State<ScansState> = _state
    private var numberOfSelectedScans = 0

    init {
        getScans()
    }

    fun getScans() {
        getScansUseCase.let { it() }.onEach { result ->
            when (result) {
                is Resource.Success -> {
                    _state.value = ScansState(scans = mutableStateOf(result.data.toMutableStateList()))
                }
                is Resource.Error -> {
                    _state.value = ScansState(
                        error = result.message ?: "An unexpected error occured"
                    )
                }
            }
        }.launchIn(viewModelScope)
    }

    fun deleteSelectedScans() {
        _state.value.scans.value.sortBy { it.isSelected.value }
        for (i in _state.value.scans.value.lastIndex downTo 0) {
            if (_state.value.scans.value[i].isSelected.value) {
                _state.value.scans.value[i].isSelected = mutableStateOf(false)
            } else
                break
        }

        numberOfSelectedScans = 0
        _state.value.isMAnageButtonVisible.value = false
    }

    fun selectScan(item: ScanItem){

        if (item.isSelected.value){
            numberOfSelectedScans --
        }
        else{
            numberOfSelectedScans ++
        }

        _state.value.isMAnageButtonVisible.value = numberOfSelectedScans > 0

        _state.value.scans.value.find { item.id == it.id }?.isSelected?.value = !item.isSelected.value
    }
}
