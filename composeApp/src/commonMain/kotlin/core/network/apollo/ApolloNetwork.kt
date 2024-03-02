package core.network.apollo

import com.apollographql.apollo3.ApolloClient
import core.model.Continent
import core.network.model.asExternalModel
import core.network.NetworkDataSource
import org.example.ContinentsQuery

class ApolloNetwork(
    private val apolloClient: ApolloClient
) : NetworkDataSource {
    override suspend fun getContinents(): List<Continent> = apolloClient
        .query(ContinentsQuery())
        .execute()
        .data
        ?.continents
        ?.map { it.asExternalModel() }
        ?: emptyList()
}
