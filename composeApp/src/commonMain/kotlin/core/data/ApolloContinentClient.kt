package core.data

import com.apollographql.apollo3.ApolloClient
import core.data.mapper.toDomainModel
import core.domain.ContinentClient
import core.domain.model.Continent
import org.example.ContinentsQuery

class ApolloContinentClient(
    private val apolloClient: ApolloClient
) : ContinentClient {
    override suspend fun getContinents(): List<Continent> = apolloClient
        .query(ContinentsQuery())
        .execute()
        .data
        ?.continents
        ?.map { it.toDomainModel() }
        ?: emptyList()
}