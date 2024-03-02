package core.network

import core.model.Continent

interface NetworkDataSource {
    suspend fun getContinents(): List<Continent>
}
