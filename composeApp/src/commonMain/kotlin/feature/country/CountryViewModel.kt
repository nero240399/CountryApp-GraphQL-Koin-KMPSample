package feature.country

import core.domain.ContinentClient
import core.domain.model.Continent
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import moe.tlaster.precompose.viewmodel.ViewModel
import moe.tlaster.precompose.viewmodel.viewModelScope

data class CountryUiState(
    val continents: List<Continent> = emptyList()
)

class CountryViewModel(
    private val continentClient: ContinentClient
) : ViewModel() {
    private val _uiState = MutableStateFlow(CountryUiState())
    val uiState = _uiState.asStateFlow()

    fun updateContinents() {
        viewModelScope.launch {
            val continents = continentClient.getContinents()
            _uiState.update {
                it.copy(continents = continents)
            }
        }
    }
}