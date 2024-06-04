package ic.ac.unpas.jadwalin.Repositories

import com.skydoves.sandwich.ApiResponse
import ic.ac.unpas.jadwalin.Repositories.responses.ClassesDeleteResponse
import id.ac.unpas.jadwalin.Repositories.responses.ClassesGetResponse
import id.ac.unpas.jadwalin.Repositories.responses.ClassesPostResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface ClassesApi {
    @GET("class")
    suspend fun findAll(): ApiResponse<ClassGetResponse>

    @POST("class")
    @Headers("Content-Type: application/json")
    suspend fun insert(@Body class: Classes): ApiResponse<ClassPostResponse>

    @PUT("class/{id}")
    @Headers("Content-Type: application/json")
    suspend fun update(@Path("id") id: String, @Body class: Classes): ApiResponse<ClassPostResponse>

    @DELETE("class/{id}")
    suspend fun delete(@Path("id") id: String): ApiResponse<ClassDeleteResponse>
}
