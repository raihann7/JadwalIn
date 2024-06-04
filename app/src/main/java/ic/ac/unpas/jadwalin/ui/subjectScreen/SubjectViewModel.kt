package ic.ac.unpas.jadwalin.ui.lecturescreen

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.switchMap
import dagger.hilt.android.lifecycle.HiltViewModel
import id.ac.unpas.agenda.base.LiveCoroutinesViewModel
import id.ac.unpas.agenda.models.Todo
import id.ac.unpas.agenda.persistences.TodoDao
import id.ac.unpas.agenda.repositories.TodoRepository
import javax.inject.Inject

@HiltViewModel
class TodoViewModel @Inject constructor(private val todoRepository: SubjectRepository) : LiveCoroutinesViewModel() {

    private val _isDone: MutableLiveData<Boolean> = MutableLiveData(false)
    val isDone: LiveData<Boolean> = _isDone

    private val _isLoading: MutableLiveData<Boolean> = MutableLiveData(false)
    val isLoading: LiveData<Boolean> = _isLoading

    private val _item: MutableLiveData<Subject> = MutableLiveData()
    val item: LiveData<Subject> = _item

    private val _todo: MutableLiveData<Boolean> = MutableLiveData(false)
    val todos : LiveData<List<Subject>> = _todo.switchMap {
        _isLoading.postValue(true)
        launchOnViewModelScope {
            todoRepository.loadItems(
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
                       title: String,
                       description: String,
                       dueDate: String) {
        _isLoading.postValue(true)
        todoRepository.insert(Subject(code, nama, decription, sks),
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

    suspend fun update(id: String,
                       title: String,
                       description: String,
                       dueDate: String) {
        _isLoading.postValue(true)
        todoRepository.update(Subject(code, nama, decription, sks),
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
        todoRepository.delete(id,
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
        val todo = todoRepository.find(id)
        todo?.let {
            _item.postValue(it)
        }
    }
}