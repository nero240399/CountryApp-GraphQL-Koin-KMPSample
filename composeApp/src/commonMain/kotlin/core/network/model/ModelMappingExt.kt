package core.network.model

import core.model.Continent
import core.model.Country
import org.example.ContinentsQuery


fun ContinentsQuery.Continent.asExternalModel() = Continent(
    name = name,
    countries = countries.map { country -> country.asExternalModel() }
)

fun ContinentsQuery.Country.asExternalModel() = Country(
    name = name,
    capital = capital ?: "No capital",
    emoji = emoji,
    currency = currency ?: "No currency",
    continent = continent.name,
    languages = languages.map { it.name }
)
