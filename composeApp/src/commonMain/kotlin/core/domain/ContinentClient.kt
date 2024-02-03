package core.domain

import core.domain.model.Continent

interface ContinentClient {
    suspend fun getContinents(): List<Continent>
}