package ic.ac.unpas.jadwalin.models

import androidx.compose.runtime.Immutable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
@Immutable
data class Lecturers(
    @PrimaryKey
    val id: String,
    val nidn: String,
    val name: String,
    val fields: String,
    @SerializedName("due_date")
    val dueDate: String
)
