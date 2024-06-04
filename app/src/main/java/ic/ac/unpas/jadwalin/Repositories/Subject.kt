package ic.ac.unpas.jadwalin.Repositories

import com.skydoves.sandwich.ApiResponse
import ic.ac.unpas.jadwalin.Repositories.responses.SubjectDeleteResponse
import id.ac.unpas.jadwalin.Repositories.responses.SubjectGetResponse
import id.ac.unpas.jadwalin.Repositories.responses.SubjectPostResponse
import retrofit2.http.Body
import retrofit2.http.DELETE
import retrofit2.http.GET
import retrofit2.http.Headers
import retrofit2.http.POST
import retrofit2.http.PUT
import retrofit2.http.Path

interface SubjectApi {
    @GET("subject")
    suspend fun findAll(): ApiResponse<SubjectGetResponse>

    @POST("subject")
    @Headers("Content-Type: application/json")
    suspend fun insert(@Body subject: Subject): ApiResponse<SubjectPostResponse>

    @PUT("subject/{id}")
    @Headers("Content-Type: application/json")
    suspend fun update(@Path("id") id: String, @Body subject: Subject): ApiResponse<SubjectPostResponse>

    @DELETE("subject/{id}")
    suspend fun delete(@Path("id") id: String): ApiResponse<SubjectDeleteResponse>
}
