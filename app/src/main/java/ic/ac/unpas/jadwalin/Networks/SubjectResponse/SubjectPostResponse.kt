package ic.ac.unpas.jadwalin.Networks.SubjectResponse

import id.ac.unpas.jadwalin.models.Subject

data class SubjectPostResponse(
    val message: String,
    val success: Boolean,
    val data: Subject?
)