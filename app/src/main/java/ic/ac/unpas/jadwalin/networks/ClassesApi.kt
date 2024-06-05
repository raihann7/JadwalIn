package ic.ac.unpas.jadwalin.networks

import com.skydoves.sandwich.ApiResponse
import ic.ac.unpas.jadwalin.networks.ClassesResponse.ClassesDeleteResponse
import ic.ac.unpas.jadwalin.networks.ClassesResponse.ClassesGetResponse
import ic.ac.unpas.jadwalin.networks.ClassesResponse.ClassesPostResponse
import id.ac.unpas.jadwalin.models.Classes
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ClassesApi {
    @GET("class")
    suspend fun findAll(): ApiResponse<ClassesGetResponse>

    @POST("class")
    @Headers("Content-Type: application/json")
    suspend fun insert(@Body classes: Classes): ApiResponse<ClassesPostResponse>

    @PUT("class/{id}")
    @Headers("Content-Type: application/json")
    suspend fun update(@Path("id") id: String, @Body classes: Classes): ApiResponse<ClassesPostResponse>

    @DELETE("class/{id}")
    suspend fun delete(@Path("id") id: String): ApiResponse<ClassesDeleteResponse>
}