package id.ac.unpas.jadwalin.di

import com.skydoves.sandwich.retrofit.adapters.ApiResponseCallAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ic.ac.unpas.jadwalin.networks.ClassesApi
import ic.ac.unpas.jadwalin.networks.LecturerApi
import ic.ac.unpas.jadwalin.networks.SubjectApi
import id.ac.unpas.jadwalin.networks.RequestInterceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object NetworkModule {

    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        return OkHttpClient.Builder().addInterceptor(RequestInterceptor()).build()
    }

    @Provides
    @Singleton
    fun provideRetrofit(okHttpClient: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .client(okHttpClient)
            .baseUrl("http://ppm-api.nimbus.biz.id/api/course/")
            .addConverterFactory(GsonConverterFactory.create())
            .addCallAdapterFactory(ApiResponseCallAdapterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideClassesApi(retrofit: Retrofit): ClassesApi {
        return retrofit.create(ClassesApi::class.java)
    }
    @Provides
    @Singleton
    fun provideLecturerApi(retrofit: Retrofit): LecturerApi {
        return retrofit.create(LecturerApi::class.java)
    }
    @Provides
    @Singleton
    fun provideSubjectApi(retrofit: Retrofit): SubjectApi {
        return retrofit.create(SubjectApi::class.java)
    }
}