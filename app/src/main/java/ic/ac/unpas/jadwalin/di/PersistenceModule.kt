package ic.ac.unpas.jadwalin.di

import android.app.Application
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ic.ac.unpas.jadwalin.persistences.AppDatabase
import ic.ac.unpas.jadwalin.persistences.ClassesDao
import ic.ac.unpas.jadwalin.persistences.LecturersDao
import ic.ac.unpas.jadwalin.persistences.SubjectsDao
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object PersistenceModule {
    @Provides
    @Singleton
    fun provideDatabase(application: Application) : AppDatabase {
        return Room.databaseBuilder(application, AppDatabase::class.java, AppDatabase.DATABASE_NAME)
            .fallbackToDestructiveMigration()
            .build()
    }

    @Provides
    @Singleton
    fun provideClassesDao(appDatabase: AppDatabase) : ClassesDao {
        return appDatabase.classesDao()
    }
    @Provides
    @Singleton
    fun provideLecturerDao(appDatabase: AppDatabase) : LecturersDao {
        return appDatabase.lecturersDao()
    }
    @Provides
    @Singleton
    fun provideSubjectDao(appDatabase: AppDatabase) : SubjectsDao {
        return appDatabase.subjectsDao()
    }
}