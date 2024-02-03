package feature.country

import core.domain.ContinentClient
import core.domain.model.Continent
import core.domain.model.Country
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
    val selectedContinent: String = "",
    val countries: List<Country> = emptyList()
)

class CountryViewModel(
    private val continentClient: ContinentClient
) : ViewModel() {
    private val _continentsUiState = MutableStateFlow(ContinentsUiState())
    val continentsUiState = _continentsUiState.asStateFlow()

    private val _countriesUiState = MutableStateFlow(CountriesUiState())
    val countriesUiState = _countriesUiState.asStateFlow()

    fun updateContinents() {
        viewModelScope.launch {
            val continents = continentClient.getContinents()
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
}