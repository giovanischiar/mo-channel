package io.schiar.mochannel.view.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Text
import io.schiar.mochannel.view.components.SettingsListView
import io.schiar.mochannel.view.components.SettingsRadioListView
import io.schiar.mochannel.view.components.TitledView
import io.schiar.mochannel.viewmodel.SettingsViewModel

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun SettingsScreen(settingsViewModel: SettingsViewModel) {
    val prefix by settingsViewModel.prefix.collectAsState()
    val url by settingsViewModel.url.collectAsState()
    val port by settingsViewModel.port.collectAsState()

    var isServerURLFocused by remember { mutableStateOf(value = false) }
    var isServerURLMenuFocused by remember { mutableStateOf(value = false) }
    var isServerURLMenuEditFocused by remember { mutableStateOf(value = false) }
    var optionFocused by remember { mutableStateOf("Prefix") }

    Row(modifier = Modifier.fillMaxHeight().padding(top = 50.dp, bottom = 50.dp, end = 50.dp)) {
        TitledView(modifier = Modifier.weight(1f), title = "Settings") {
            SettingsListView(
                labelValue = listOf(
                    Pair(
                        "Server URL",
                        "${prefix.lowercase()}://$url${if (port.isEmpty()) "" else ":$port"}"
                    )
                ),
                firstItemFocused = true,
                onIndexFocusChanged = { _, isFocused -> isServerURLFocused = isFocused }
            )
        }

        if (isServerURLFocused || isServerURLMenuFocused || isServerURLMenuEditFocused) {
            val labelValue = listOf(
                Pair("URL", url),
                Pair("Port", port),
                Pair("Prefix", prefix)
            )
            TitledView(modifier = Modifier.weight(1f), title = "Server URL") {
                SettingsListView(
                    modifier = Modifier.weight(1f),
                    labelValue = labelValue,
                    onIndexFocusChanged = { index, focus ->
                        if (focus) {
                            optionFocused = labelValue[index].first;
                            isServerURLMenuFocused = true
                        }
                    },
                )
            }
        }

        if (!isServerURLFocused && (isServerURLMenuFocused || isServerURLMenuEditFocused)) {
            Box(modifier = Modifier.weight(1f).fillMaxHeight()) {
                when (optionFocused) {
                    "Prefix" -> {
                        val prefixes = listOf("HTTP", "HTTPS")
                        TitledView(title = "Prefix") {
                            SettingsRadioListView(
                                values = prefixes,
                                currentSelectedValue = prefix,
                                onListItemValuePressed = settingsViewModel::updatePrefixTo,
                                onListItemFocusChanged = { _, focus ->
                                    if (focus) {
                                        isServerURLMenuEditFocused = true
                                    }
                                }
                            )
                        }
                    }

                    "URL" -> {
                        val options = listOf("Use numeric Keyboard", "Use full Keyboard")
                        var selectedOption by remember { mutableStateOf(value = options[0]) }
                        val keyboardType = when (options.indexOf(selectedOption)) {
                            0 -> KeyboardType.Number
                            else -> KeyboardType.Uri
                        }
                        TitledView(title = "URL") {
                            TextField(
                                modifier = Modifier.padding(
                                    start = 50.dp, top = 5.dp, bottom = 5.dp
                                ),
                                keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
                                value = url,
                                onValueChange = { value ->
                                    settingsViewModel.updateURLTo(
                                        newURL = if (options.indexOf(selectedOption) == 0) {
                                            value
                                                .replace(oldValue = ",", newValue = "")
                                                .replace(oldValue = "-", newValue = "")
                                                .replace(oldValue = " ", newValue = "")
                                        } else { value }
                                    )
                                },
                                label = { Text("URL") },
                                singleLine = true
                            )

                            SettingsRadioListView(
                                values = options,
                                currentSelectedValue = selectedOption,
                                onListItemValuePressed = { selectedOption = it }
                            )
                        }
                    }

                    "Port" -> {
                        val keyboardType = KeyboardType.Number
                        TitledView(title = "Port") {
                            TextField(
                                modifier = Modifier.padding(
                                    start = 50.dp, top = 5.dp, bottom = 5.dp
                                ),
                                keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
                                value = port,
                                onValueChange = settingsViewModel::updatePortTo,
                                label = { Text("Port") },
                                singleLine = true
                            )
                        }
                    }
                }
            }
        }
    }
}