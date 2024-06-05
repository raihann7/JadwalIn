package ic.ac.unpas.jadwalin.persistences

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import id.ac.unpas.jadwalin.models.Classes

@Dao
interface ClassesDao {

    @Query("select * from classes")
    fun loadAll(): LiveData<List<Classes>>

    @Query("select * from classes")
    suspend fun findAll(): List<Classes>

    @Query("select * from classes where id = :id")
    fun load(id: String): LiveData<Classes>

    @Query("select * from classes where id = :id")
    suspend fun getById(id: String): Classes?

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(vararg items: Classes)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(list: List<Classes>)

    @Query("delete from classes where id = :id")
    suspend fun delete(id: String)

    @Query("select * from classes where id = :id")
    suspend fun find(id: String): Classes?

    @Delete
    suspend fun delete(item: Classes)
}