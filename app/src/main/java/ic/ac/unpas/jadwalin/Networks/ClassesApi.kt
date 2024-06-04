package ic.ac.unpas.jadwalin.Networks

import com.skydoves.sandwich.ApiResponse
import id.ac.unpas.jadwalin.models.Classes
import id.ac.unpas.jadwalin.Networks.responses.ClassDeleteResponse
import id.ac.unpas.jadwalin.Networks.responses.ClassGetResponse
import id.ac.unpas.jadwalin.Networks.responses.ClassPostResponse
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
    suspend fun insert(@Body class: Class): ApiResponse<ClassPostResponse>

    @PUT("class/{id}")
    @Headers("Content-Type: application/json")
    suspend fun update(@Path("id") id: String, @Body class: Class): ApiResponse<ClassPostResponse>

    @DELETE("class/{id}")
    suspend fun delete(@Path("id") id: String): ApiResponse<ClassDeleteResponse>
}