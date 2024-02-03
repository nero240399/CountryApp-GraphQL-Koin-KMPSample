package core.data.mapper

import core.domain.model.Continent
import org.example.ContinentsQuery


fun ContinentsQuery.Continent.toDomainModel() = Continent(
    name = name,
    countries = countries.map { it.toCountry() }
)