package ic.ac.unpas.jadwalin.ui.screens.subjectScreen

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.switchMap
import dagger.hilt.android.lifecycle.HiltViewModel
import ic.ac.unpas.jadwalin.base.LiveCoroutinesViewModel
import ic.ac.unpas.jadwalin.models.Subjects
import ic.ac.unpas.jadwalin.repositories.SubjectRepository
import javax.inject.Inject

@HiltViewModel
class SubjectViewModel @Inject constructor(private val subjectRepository: SubjectRepository) : LiveCoroutinesViewModel() {

    private val _isDone: MutableLiveData<Boolean> = MutableLiveData(false)
    val isDone: LiveData<Boolean> = _isDone

    private val _isLoading: MutableLiveData<Boolean> = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading

    private val _item: MutableLiveData<Subjects> = MutableLiveData()
    val item: LiveData<Subjects> = _item

    private val _todo: MutableLiveData<Boolean> = MutableLiveData(false)
    val subjects : LiveData<List<Subjects>> = _todo.switchMap {
        _isLoading.postValue(true)
        launchOnViewModelScope {
            subjectRepository.loadItems(
                onSuccess = {
                    _isLoading.postValue(false)
                },
                onError = {
                    _isLoading.postValue(false)
                    Log.e("TodoViewModel", it)
                }
            ).asLiveData()
        }
    }

    suspend fun insert(
        id: String,
        code: String,
        name: String,
        description: String,
        sks: String
    ) {
        _isLoading.postValue(true)
        subjectRepository.insert(Subjects(id, code, name, description, sks),
            onSuccess = {
                _isLoading.postValue(false)
                _isDone.postValue(true)
                _todo.postValue(true)
            },
            onError = {
                _isLoading.postValue(false)
                _isDone.postValue(true)
                _todo.postValue(true)
            }
        )
    }

    suspend fun update(
        id: String,
        code: String,
        name: String,
        description: String,
        sks: String
    ) {
        _isLoading.postValue(true)
        subjectRepository.update(Subjects(id, code, name, description, sks),
            onSuccess = {
                _isLoading.postValue(false)
                _isDone.postValue(true)
                _todo.postValue(true)
            },
            onError = {
                _isLoading.postValue(false)
                _isDone.postValue(true)
                _todo.postValue(true)
            }
        )
    }

    suspend fun delete(id: String) {
        _isLoading.postValue(true)
        subjectRepository.delete(id,
            onSuccess = {
                _isLoading.postValue(false)
                _isDone.postValue(true)
                _todo.postValue(true)
            },
            onError = {
                _isLoading.postValue(false)
                _isDone.postValue(true)
                _todo.postValue(true)
            }
        )
    }

    suspend fun find(id: String) {
        val todo = subjectRepository.find(id)
        todo?.let {
            _item.postValue(it)
        }
    }
}