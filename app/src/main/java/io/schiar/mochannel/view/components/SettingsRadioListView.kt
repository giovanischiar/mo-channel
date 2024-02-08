package io.schiar.mochannel.view.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.tv.foundation.lazy.list.TvLazyColumn
import androidx.tv.material3.Button
import androidx.tv.material3.ButtonDefaults
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.RadioButton
import androidx.tv.material3.RadioButtonDefaults
import androidx.tv.material3.Text
import io.schiar.mochannel.R

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun SettingsRadioListView(
    modifier: Modifier = Modifier,
    values: List<String> = listOf(""),
    currentSelectedValue: String = values[0],
    firstItemFocused: Boolean = false,
    onListItemValuePressed: (value: String) -> Unit = {},
    onListItemFocusChanged: (index: Int, focus: Boolean) -> Unit = { _, _ -> },
) {
    val focusRequester = remember { FocusRequester() }
    LaunchedEffect(Unit) { if (firstItemFocused) { focusRequester.requestFocus() } }
    Column(modifier = modifier.fillMaxWidth()) {
        TvLazyColumn {
            items(values.size) { index ->
                val value = values[index]
                val firstItemModifier = if (firstItemFocused && index == 0) {
                    Modifier.focusRequester(focusRequester)
                } else {
                    Modifier
                }
                Button(
                    modifier =
                    firstItemModifier.padding(start = 50.dp, top = 5.dp, bottom = 5.dp)
                        .onFocusChanged {
                            onListItemFocusChanged(index, it.hasFocus)
                        },
                    onClick = { onListItemValuePressed(value) },
                    colors = ButtonDefaults.colors(
                        containerColor = Color.Transparent,
                        focusedContainerColor = colorResource(id = R.color.mColor)
                    )
                ) {
                    Box(modifier = Modifier.fillMaxWidth()) {
                        Row {
                            Box(modifier = Modifier.height(45.dp).padding(end = 10.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                RadioButton(
                                    selected = currentSelectedValue == value,
                                    onClick = { onListItemValuePressed(value) },
                                    colors = RadioButtonDefaults.colors(
                                        selectedColor = colorResource(R.color.moonColor),
                                        unselectedColor = colorResource(R.color.moonColor),
                                        disabledSelectedColor = colorResource(
                                            R.color.moonColor
                                        ),
                                        disabledUnselectedColor = colorResource(
                                            R.color.moonColor
                                        )
                                    )
                                )
                            }
                            Box(modifier = Modifier.height(45.dp),
                                contentAlignment = Alignment.Center
                            ) {
                                Text(
                                    value,
                                    style = MaterialTheme.typography.bodyLarge,
                                    color = colorResource(R.color.moonColor)
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}