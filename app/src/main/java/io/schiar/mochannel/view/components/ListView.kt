package io.schiar.mochannel.view.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.onFocusEvent
import androidx.compose.ui.unit.dp
import androidx.tv.material3.Button
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Text

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun ListView(
    modifier: Modifier = Modifier,
    buttonTitles: List<String>,
    onButtonPressedAt: (index: Int) -> Unit,
    onButtonFocusedAt: (index: Int) -> Unit = {}
) {
    LazyColumn(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        items(buttonTitles.size) { index ->
            val buttonTitle = buttonTitles[index]
            Button(modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 50.dp, vertical = 5.dp)
                .onFocusEvent {
                    if (it.hasFocus) { onButtonFocusedAt(index) }
                },
                onClick= { onButtonPressedAt(index) }
            ) {
                Text(buttonTitle)
            }
        }
    }
}