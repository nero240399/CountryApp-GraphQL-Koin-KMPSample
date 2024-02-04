package core.domain.model

data class Country(
    val name: String,
    val emoji: String,
    val capital: String,
    val currency: String,
    val languages: List<String>,
    val continent: String
)
