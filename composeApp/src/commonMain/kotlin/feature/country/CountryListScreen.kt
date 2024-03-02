package feature.country

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.staggeredgrid.LazyVerticalStaggeredGrid
import androidx.compose.foundation.lazy.staggeredgrid.StaggeredGridCells
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.window.Dialog
import core.model.Country

@Composable
fun CountryListScreen(
    state: CountriesUiState,
    onClickBack: () -> Unit,
    onClickCountry: (String?) -> Unit
) {
    Scaffold(
        topBar = { TopAppBar(state.selectedContinent!!, onClickBack) }
    ) { paddingValues ->
        LazyVerticalStaggeredGrid(
            modifier = Modifier.fillMaxSize().padding(paddingValues),
            columns = StaggeredGridCells.Fixed(2),
            contentPadding = PaddingValues(8.dp),
            verticalItemSpacing = 8.dp,
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            state.countries.forEach {
                item {
                    CountryCard(capital = it.capital, name = it.name, emoji = it.emoji) {
                        onClickCountry(it.name)
                    }
                }
            }
        }

        if (state.selectedCountry != null) {
            CountryDialog(
                country = state.selectedCountry,
                onDismiss = { onClickCountry(null) },
            )
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopAppBar(
    title: String,
    onClickBack: () -> Unit
) {
    MediumTopAppBar(
        title = { Text(title) },
        navigationIcon = {
            IconButton(onClick = onClickBack) {
                Icon(
                    Icons.AutoMirrored.Filled.ArrowBack,
                    "Go back"
                )
            }
        })
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun CountryCard(
    modifier: Modifier = Modifier,
    capital: String,
    name: String,
    emoji: String,
    onClick: () -> Unit = {}
) {
    Card(onClick = onClick) {
        Column(modifier = modifier.padding(vertical = 8.dp, horizontal = 16.dp)) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Text(emoji, style = MaterialTheme.typography.bodyLarge)
                Spacer(Modifier.width(8.dp))
                Text(name, style = MaterialTheme.typography.titleSmall)
            }
            Spacer(Modifier.height(8.dp))
            Text("Capital: $capital", style = MaterialTheme.typography.bodyLarge)
        }
    }
}

@Composable
fun CountryDialog(
    modifier: Modifier = Modifier,
    country: Country,
    onDismiss: () -> Unit,
) {
    val joinedLanguages = remember(country.languages) {
        country.languages.joinToString()
    }
    Dialog(onDismissRequest = onDismiss) {
        Card(modifier) {
            Column(
                Modifier.padding(16.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                Row(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = country.emoji,
                        style = MaterialTheme.typography.titleLarge
                    )
                    Spacer(modifier = Modifier.width(8.dp))
                    Text(
                        text = country.name,
                        style = MaterialTheme.typography.titleMedium
                    )
                }
                Spacer(Modifier)
                Text(text = "Continent: " + country.continent)
                Text(text = "Currency: " + country.currency)
                Text(text = "Capital: " + country.capital)
                Text(text = "Language(s): $joinedLanguages")
            }
        }
    }
}
