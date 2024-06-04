package ic.ac.unpas.jadwalin.Repositories

import com.skydoves.sandwich.ApiResponse
import ic.ac.unpas.jadwalin.Repositories.responses.LecturerDeleteResponse
import id.ac.unpas.jadwalin.Repositories.responses.LecturerGetResponse
import id.ac.unpas.jadwalin.Repositories.responses.LecturerPostResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface LecturerApi {
    @GET("lecturer")
    suspend fun findAll(): ApiResponse<LecturerGetResponse>

    @POST("lecturer")
    @Headers("Content-Type: application/json")
    suspend fun insert(@Body lecturer: Lecturer): ApiResponse<LecturerPostResponse>

    @PUT("lecturer/{id}")
    @Headers("Content-Type: application/json")
    suspend fun update(@Path("id") id: String, @Body lecturer: Lecturer): ApiResponse<LecturerPostResponse>

    @DELETE("lecturer/{id}")
    suspend fun delete(@Path("id") id: String): ApiResponse<LecturerDeleteResponse>
}
