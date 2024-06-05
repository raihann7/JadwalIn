package ic.ac.unpas.jadwalin.di.RepositoryModule

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import ic.ac.unpas.jadwalin.networks.ClassesApi
import ic.ac.unpas.jadwalin.networks.LecturerApi
import ic.ac.unpas.jadwalin.networks.SubjectApi
import ic.ac.unpas.jadwalin.persistences.ClassesDao
import ic.ac.unpas.jadwalin.persistences.LecturersDao
import ic.ac.unpas.jadwalin.persistences.SubjectsDao
import ic.ac.unpas.jadwalin.repositories.ClassesRepository
import ic.ac.unpas.jadwalin.repositories.LecturerRepository
import ic.ac.unpas.jadwalin.repositories.SubjectRepository

@Module
@InstallIn(ViewModelComponent::class)
object RepositoryModule {

    @Provides
    @ViewModelScoped
    fun provideClassesRepository(classesDao: ClassesDao, classesApi: ClassesApi): ClassesRepository {
        return ClassesRepository(classesApi, classesDao)
    }
    fun provideLecturersRepository(lecturersDao: LecturersDao, lecturerApi: LecturerApi ): LecturerRepository {
        return LecturerRepository(lecturerApi, lecturersDao)
    }
    fun provideSubjectsRepository(subjectsDao: SubjectsDao, subjectsApi: SubjectApi): SubjectRepository {
        return SubjectRepository(subjectsApi, subjectsDao)
    }
}