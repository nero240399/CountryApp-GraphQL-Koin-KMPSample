package feature.country

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.SuggestionChip
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun ContinentsScreen(
    state: ContinentsUiState,
    navigateToCountries: (String) -> Unit
) {
    Box(Modifier.fillMaxSize(), Alignment.Center) {
        if (state.isLoading) {
            Text("loading...")
        } else {
            Column(horizontalAlignment = Alignment.CenterHorizontally) {
                Text(
                    "Continents \uD83C\uDF0E",
                    style = MaterialTheme.typography.displaySmall,
                )
                Spacer(Modifier.height(32.dp))
                FlowRow(
                    Modifier.padding(horizontal = 8.dp),
                    horizontalArrangement = Arrangement.spacedBy(8.dp),
                ) {
                    for (continent in state.continents) {
                        SuggestionChip(
                            onClick = { navigateToCountries(continent.name) },
                            label = {
                                Text(
                                    continent.name,
                                    style = MaterialTheme.typography.titleMedium
                                )
                            })
                    }
                }
            }
        }
    }
}