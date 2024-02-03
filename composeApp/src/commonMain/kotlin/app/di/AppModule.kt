package app.di

import com.apollographql.apollo3.ApolloClient
import core.data.ApolloContinentClient
import core.domain.ContinentClient
import feature.country.CountryViewModel
import org.koin.core.module.Module
import org.koin.dsl.module

fun appModule(): Module = module {
    single { ApolloClient.Builder().serverUrl("https://countries.trevorblades.com/graphql").build() }
    single<ContinentClient> { ApolloContinentClient(get()) }
    factory {
        CountryViewModel(get())
    }
}