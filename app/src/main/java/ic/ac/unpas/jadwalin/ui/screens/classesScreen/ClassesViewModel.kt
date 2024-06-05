package ic.ac.unpas.jadwalin.ui.screens.ClassesViewModel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.switchMap
import dagger.hilt.android.lifecycle.HiltViewModel
import ic.ac.unpas.jadwalin.base.LiveCoroutinesViewModel
import ic.ac.unpas.jadwalin.repositories.ClassesRepository
import id.ac.unpas.jadwalin.models.Classes
import javax.inject.Inject

@HiltViewModel
class ClassesViewModel @Inject constructor(private val classesRepository: ClassesRepository) : LiveCoroutinesViewModel() {

    private val _isDone: MutableLiveData<Boolean> = MutableLiveData(false)
    val isDone: LiveData<Boolean> = _isDone

    private val _isLoading: MutableLiveData<Boolean> = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading

    private val _item: MutableLiveData<Classes> = MutableLiveData()
    val item: LiveData<Classes> = _item

    private val _isDeleted: MutableLiveData<Boolean> = MutableLiveData(false)
    val isDeleted: LiveData<Boolean> = _isDeleted

    private val _classes: MutableLiveData<Boolean> = MutableLiveData(false)
    val classess : LiveData<List<Classes>> = _classes.switchMap {
        _isLoading.postValue(true)
        launchOnViewModelScope {
            classesRepository.loadItems(
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

    suspend fun insert(id: String,
                       lecturer_id: String,
                       subject_id: String,
                       name: String,
                       day: String,
                       start_time: String,
                       end_time: String) {
        _isLoading.postValue(true)
        classesRepository.insert(Classes(id, lecturer_id, subject_id, name, day, start_time, end_time),
            onSuccess = {
                _isLoading.postValue(false)
                _isDone.postValue(true)
                _classes.postValue(true)
            },
            onError = {
                _isLoading.postValue(false)
                _isDone.postValue(true)
                _classes.postValue(true)
            }
        )
    }

    suspend fun update(id: String,
                       lecturer_id: String,
                       subject_id: String,
                       name: String,
                       day: String,
                       start_time: String,
                       end_time: String) {
        _isLoading.postValue(true)
        classesRepository.update(Classes(id, lecturer_id, subject_id, name, day, start_time, end_time),
            onSuccess = {
                _isLoading.postValue(false)
                _isDone.postValue(true)
                _classes.postValue(true)
            },
            onError = {
                _isLoading.postValue(false)
                _isDone.postValue(true)
                _classes.postValue(true)
            }
        )
    }

    suspend fun delete(id: String) {
        _isLoading.postValue(true)
        classesRepository.delete(id,
            onSuccess = {
                _isLoading.postValue(false)
                _isDone.postValue(true)
                _classes.postValue(true)
                _isDeleted.postValue(true)
            },
            onError = {
                _isLoading.postValue(false)
                _isDone.postValue(true)
                _classes.postValue(true)
                _isDeleted.postValue(false)
            }
        )
    }

    suspend fun find(id: String) {
        val classes = classesRepository.find(id)
        classes?.let {
            _item.postValue(it)
        }
    }
}