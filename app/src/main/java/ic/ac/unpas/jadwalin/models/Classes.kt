package id.ac.unpas.agenda.models

import androidx.compose.runtime.Immutable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
@Immutable
data class Classes(
    @PrimaryKey
    val id: String,
    val lecturer_id: String,
    val subject_id: String,
    val name: String,
    val day: String,
    val star_time: String,
    val end_time: String,

    @SerializedName("due_date")
    val dueDate: String
)
