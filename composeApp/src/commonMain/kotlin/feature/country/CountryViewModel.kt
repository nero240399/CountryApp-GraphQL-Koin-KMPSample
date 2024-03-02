package feature.country

import core.model.Continent
import core.network.NetworkDataSource
import core.model.Country
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import moe.tlaster.precompose.viewmodel.ViewModel
import moe.tlaster.precompose.viewmodel.viewModelScope

data class ContinentsUiState(
    val continents: List<Continent> = emptyList(),
    val isLoading: Boolean = true
)

data class CountriesUiState(
    val selectedContinent: String? = null,
    val selectedCountry: Country? = null,
    val countries: List<Country> = emptyList()

)

class CountryViewModel(
    private val networkDataSource: NetworkDataSource
) : ViewModel() {
    private val _continentsUiState = MutableStateFlow(ContinentsUiState())
    val continentsUiState = _continentsUiState.asStateFlow()

    private val _countriesUiState = MutableStateFlow(CountriesUiState())
    val countriesUiState = _countriesUiState.asStateFlow()

    fun updateContinents() {
        viewModelScope.launch {
            val continents = networkDataSource.getContinents()
            _continentsUiState.update { state ->
                state.copy(continents = continents, isLoading = false)
            }
        }
    }

    fun selectContinent(name: String) {
        _countriesUiState.update { state ->
            state.copy(
                selectedContinent = name,
                countries = continentsUiState.value.continents
                    .find { it.name == name }!!.countries
            )
        }
    }

    fun selectCountry(name: String?) {
        _countriesUiState.update { state ->
            if (name != null) {
                state.copy(selectedCountry = state.countries.find { it.name == name })
            } else {
                state.copy(selectedCountry = null)
            }
        }
    }
}