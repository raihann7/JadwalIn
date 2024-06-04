package ic.ac.unpas.jadwalin.Networks.LecturerResponse

import id.ac.unpas.jadwalin.models.Lecturer

data class LecturerPostResponse(
    val message: String,
    val success: Boolean,
    val data: Lecturer?
)
