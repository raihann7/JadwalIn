package ic.ac.unpas.jadwalin.networks.ClassesResponse

import id.ac.unpas.jadwalin.models.Classes

data class ClassesPostResponse(
    val message: String,
    val success: Boolean,
    val data: Classes?
)
