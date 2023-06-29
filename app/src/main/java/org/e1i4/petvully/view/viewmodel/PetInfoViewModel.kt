package org.e1i4.petvully.view.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import org.e1i4.petvully.data.remote.model.ResponsePetsInfo
import org.e1i4.petvully.data.remote.model.ResponsePetsInfoItem
import org.e1i4.petvully.di.NetworkModule

class PetInfoViewModel(application: Application) : AndroidViewModel(application) {
    private val _petList = MutableLiveData<ResponsePetsInfo>()
    val petList: LiveData<ResponsePetsInfo>
        get() = _petList

    fun requestPetList() = viewModelScope.launch(Dispatchers.IO){
        _petList.postValue(
            NetworkModule.provideCongressService().petList()
        )
    }
}