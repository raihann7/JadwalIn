package ic.ac.unpas.jadwalin.models

import androidx.compose.runtime.Immutable
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
@Immutable
data class Lecturers(
    @PrimaryKey
    val id: String,
    val nidn: String,
    val name: String,
    val fields: String
)
