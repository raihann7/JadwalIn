package ic.ac.unpas.jadwalin.persistences

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ic.ac.unpas.jadwalin.models.Lecturers

@Dao
interface LecturersDao {

    @Query("select * from lecturers")
    fun loadAll(): LiveData<List<Lecturers>>

    @Query("select * from lecturers")
    suspend fun findAll(): List<Lecturers>

    @Query("select * from lecturers where id = :id")
    fun load(id: String): LiveData<Lecturers>

    @Query("select * from lecturers where id = :id")
    suspend fun getById(id: String): Lecturers?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(vararg items: Lecturers)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(list: List<Lecturers>)

    @Query("delete from lecturers where id = :id")
    suspend fun delete(id: String)

    @Query("select * from lecturers where id = :id")
    suspend fun find(id: String): Lecturers?

    @Delete
    suspend fun delete(item: Lecturers)
}