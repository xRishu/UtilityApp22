package com.rk.utility22.MainViewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.rk.utility22.Model.Member
import com.rk.utility22.repository.repository
import kotlinx.coroutines.launch
import retrofit2.Response

class MainViewModel(private val repository: repository): ViewModel() {
    val myResponse: MutableLiveData<Response<List<Member>>> = MutableLiveData()

    fun getPost(){
        viewModelScope.launch{
            val response : Response<List<Member>> = repository.getMember()
//                myResponse.value=response
            myResponse.postValue(response)
        }
    }
}