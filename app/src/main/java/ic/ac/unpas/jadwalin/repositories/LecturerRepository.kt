package ic.ac.unpas.jadwalin.repositories

import androidx.annotation.WorkerThread
import com.skydoves.sandwich.message
import com.skydoves.sandwich.suspendOnError
import com.skydoves.sandwich.suspendOnException
import com.skydoves.sandwich.suspendOnSuccess
import com.skydoves.whatif.whatIfNotNull
import ic.ac.unpas.jadwalin.models.Lecturers
import ic.ac.unpas.jadwalin.networks.LecturerApi
import ic.ac.unpas.jadwalin.persistences.LecturersDao
import kotlinx.coroutines.flow.flow
import javax.inject.Inject

class LecturerRepository @Inject constructor(private val api: LecturerApi, private val dao: LecturersDao) {

    @WorkerThread
    fun loadItems(onSuccess: () -> Unit,
                  onError: (String) -> Unit) = flow {
        val list: List<Lecturers> = dao.findAll()
        api.findAll()
            .suspendOnSuccess {
                data.whatIfNotNull {
                    dao.upsert(it.data)
                    val localList = dao.findAll()
                    emit(localList)
                    onSuccess()
                }
            }
            .suspendOnError {
                emit(list)
                onError(message())
            }
            .suspendOnException {
                emit(list)
                onError(message())
            }
    }

    suspend fun insert(lecturers: Lecturers,
                       onSuccess: () -> Unit,
                       onError: (String) -> Unit) {
        dao.upsert(lecturers)
        api.insert(lecturers)
            .suspendOnSuccess {
                data.whatIfNotNull {
                    if (it.success) {
                        onSuccess()
                    } else {
                        onError(it.message)
                    }
                }
            }
            .suspendOnError {
                onError(message())
            }
            .suspendOnException {
                onError(message())
            }
    }

    suspend fun update(lecturers: Lecturers,
                       onSuccess: () -> Unit,
                       onError: (String) -> Unit) {
        dao.upsert(lecturers)
        api.update(lecturers.id, lecturers)
            .suspendOnSuccess {
                data.whatIfNotNull {
                    if (it.success) {
                        onSuccess()
                    } else {
                        onError(it.message)
                    }
                }
            }
            .suspendOnError {
                onError(message())
            }
            .suspendOnException {
                onError(message())
            }
    }

    suspend fun delete(id: String,
                       onSuccess: () -> Unit,
                       onError: (String) -> Unit) {
        dao.delete(id)
        api.delete(id)
            .suspendOnSuccess {
                data.whatIfNotNull {
                    if (it.success) {
                        onSuccess()
                    } else {
                        onError(it.message)
                    }
                }
            }
            .suspendOnError {
                onError(message())
            }
            .suspendOnException {
                onError(message())
            }
    }

    suspend fun find(id: String) = dao.find(id)
}