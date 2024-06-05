package ic.ac.unpas.jadwalin.persistences

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import ic.ac.unpas.jadwalin.models.Subjects

@Dao
interface SubjectsDao {

    @Query("select * from subjects")
    fun loadAll(): LiveData<List<Subjects>>

    @Query("select * from subjects")
    suspend fun findAll(): List<Subjects>

    @Query("select * from subjects where id = :id")
    fun load(id: String): LiveData<Subjects>

    @Query("select * from subjects where id = :id")
    suspend fun getById(id: String): Subjects?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(vararg items: Subjects)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(list: List<Subjects>)

    @Query("delete from subjects where id = :id")
    suspend fun delete(id: String)

    @Query("select * from subjects where id = :id")
    suspend fun find(id: String): Subjects?

    @Delete
    suspend fun delete(item: Subjects)
}