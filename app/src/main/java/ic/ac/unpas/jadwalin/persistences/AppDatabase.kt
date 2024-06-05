package ic.ac.unpas.jadwalin.persistences

import androidx.room.Database
import androidx.room.RoomDatabase
import ic.ac.unpas.jadwalin.models.Lecturers
import ic.ac.unpas.jadwalin.models.Subjects
import id.ac.unpas.jadwalin.models.Classes


@Database(entities = [
    Classes::class,
    Subjects::class,
    Lecturers::class
                     ], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun classesDao(): ClassesDao
    abstract fun lecturersDao(): LecturersDao
    abstract fun subjectsDao(): SubjectsDao

    companion object {
        const val DATABASE_NAME = "agenda-database"
    }
}