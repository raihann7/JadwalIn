package ic.ac.unpas.jadwalin.models

import androidx.compose.runtime.Immutable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
@Immutable
data class Subjects(
    @PrimaryKey
    val id: String,
    val code: String,
    val name: String,
    val description: String,
    val sks: String
)
