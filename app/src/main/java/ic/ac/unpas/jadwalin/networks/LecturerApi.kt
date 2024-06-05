package ic.ac.unpas.jadwalin.networks

import com.skydoves.sandwich.ApiResponse
import ic.ac.unpas.jadwalin.networks.LecturerResponse.LecturerGetResponse
import ic.ac.unpas.jadwalin.models.Lecturers
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
    suspend fun insert(@Body lecturer: Lecturers): ApiResponse<LecturerPostResponse>

    @PUT("lecturer/{id}")
    @Headers("Content-Type: application/json")
    suspend fun update(@Path("id") id: String, @Body lecturer: Lecturers): ApiResponse<LecturerPostResponse>

    @DELETE("lecturer/{id}")
    suspend fun delete(@Path("id") id: String): ApiResponse<LecturerDeleteResponse>
}