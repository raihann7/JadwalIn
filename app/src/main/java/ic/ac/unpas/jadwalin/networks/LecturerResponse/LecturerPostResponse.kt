package ic.ac.unpas.jadwalin.networks

import ic.ac.unpas.jadwalin.models.Lecturers

data class LecturerPostResponse(
    val message: String,
    val success: Boolean,
    val data: Lecturers?
)
