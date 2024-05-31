package io.schiar.mochannel.view.settings.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.tv.foundation.lazy.list.TvLazyColumn
import androidx.tv.material3.Button
import androidx.tv.material3.ButtonDefaults
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Text
import io.schiar.mochannel.R

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun SettingsListView(
    modifier: Modifier = Modifier,
    labelValue: List<Pair<String, String>> = emptyList(),
    firstItemFocused: Boolean = false,
    onSettingListItemPressed: (index: Int) -> Unit = {},
    onIndexFocusChanged: (index: Int, focus: Boolean) -> Unit = { _, _ -> },
) {
    val focusRequester = remember { FocusRequester() }
    LaunchedEffect(Unit) { if (firstItemFocused) { focusRequester.requestFocus() } }
    Column(modifier = modifier.fillMaxWidth()) {
        TvLazyColumn {
            items(labelValue.size) { index ->
                val (label, value) = labelValue[index]
                val firstItemModifier = if (firstItemFocused && index == 0) {
                    Modifier.focusRequester(focusRequester)
                } else {
                    Modifier
                }
                Button(
                    modifier =
                    firstItemModifier.padding(start = 50.dp, top = 5.dp, bottom = 5.dp)
                        .onFocusChanged {
                            onIndexFocusChanged(index, it.hasFocus)
                        },
                    onClick = { onSettingListItemPressed(index) },
                    colors = ButtonDefaults.colors(
                        focusedContainerColor = colorResource(id = R.color.mColor)
                    )
                ) {
                    Box(modifier = Modifier.fillMaxWidth()) {
                        Row {
                            Column {
                                Text(
                                    modifier = Modifier.padding(bottom = 5.dp),
                                    text = label,
                                    style = MaterialTheme.typography.labelMedium
                                )
                                Text(value, style = MaterialTheme.typography.bodyMedium)
                            }
                        }
                    }
                }
            }
        }
    }
}