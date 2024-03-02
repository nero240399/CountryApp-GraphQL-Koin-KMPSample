package app.di

import com.apollographql.apollo3.ApolloClient
import core.network.NetworkDataSource
import feature.country.CountryViewModel
import org.koin.core.module.Module
import core.network.apollo.ApolloNetwork
import org.koin.dsl.module

fun appModule(): Module = module {
    single { ApolloClient.Builder().serverUrl("https://countries.trevorblades.com/graphql").build() }
    single<NetworkDataSource> { ApolloNetwork(get()) }
    factory {
        CountryViewModel(get())
    }
}
