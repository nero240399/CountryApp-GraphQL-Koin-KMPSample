package core.data.mapper

import core.domain.model.Country
import org.example.ContinentsQuery

fun ContinentsQuery.Country.toCountry() = Country(
    name = name,
    capital = capital ?: "No capital",
    emoji = emoji
)