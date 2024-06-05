package ic.ac.unpas.jadwalin.ui.screens.lecturescreen

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.asLiveData
import androidx.lifecycle.switchMap
import dagger.hilt.android.lifecycle.HiltViewModel
import ic.ac.unpas.jadwalin.base.LiveCoroutinesViewModel
import ic.ac.unpas.jadwalin.models.Lecturers
import ic.ac.unpas.jadwalin.repositories.LecturerRepository
import javax.inject.Inject

@HiltViewModel
class LectureViewModel @Inject constructor(private val classesRepository: LecturerRepository) : LiveCoroutinesViewModel() {

    private val _isDone: MutableLiveData<Boolean> = MutableLiveData(false)
    val isDone: LiveData<Boolean> = _isDone

    private val _isLoading: MutableLiveData<Boolean> = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading

    private val _item: MutableLiveData<Lecturers> = MutableLiveData()
    val item: LiveData<Lecturers> = _item

    private val _isDeleted: MutableLiveData<Boolean> = MutableLiveData(false)
    val isDeleted: LiveData<Boolean> = _isDeleted

    private val _classes: MutableLiveData<Boolean> = MutableLiveData(false)
    val lecturers : LiveData<List<Lecturers>> = _classes.switchMap {
        _isLoading.postValue(true)
        launchOnViewModelScope {
            classesRepository.loadItems(
                onSuccess = {
                    _isLoading.postValue(false)
                },
                onError = {
                    _isLoading.postValue(false)
                    Log.e("LectureViewModel", it)
                }
            ).asLiveData()
        }
    }

    suspend fun insert(id: String,
                       nidn: String,
                       name: String,
                       fields: String) {
        _isLoading.postValue(true)
        classesRepository.insert(Lecturers(id, nidn, name, fields),
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
                       nidn: String,
                       name: String,
                       fields: String) {
        _isLoading.postValue(true)
        classesRepository.update(Lecturers(id, nidn, name, fields),
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