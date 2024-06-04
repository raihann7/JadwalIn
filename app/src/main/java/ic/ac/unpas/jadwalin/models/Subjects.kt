package ic.ac.unpas.jadwalin.models

import androidx.compose.runtime.Immutable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName

@Entity
@Immutable
data class Subjects(
    @PrimaryKey
    val id: String,
    val code: String,
    val name: String,
    val decription: String,
    @SerializedName("due_date")
    val dueDate: String

)
