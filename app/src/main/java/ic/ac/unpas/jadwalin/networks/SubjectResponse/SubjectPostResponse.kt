package ic.ac.unpas.jadwalin.networks

import ic.ac.unpas.jadwalin.models.Subjects

data class SubjectPostResponse(
    val message: String,
    val success: Boolean,
    val data: Subjects?
)