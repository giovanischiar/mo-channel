package io.schiar.mochannel.view.settings.components

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.TextRange
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.Text

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun TextFieldView(
    label: String,
    text: String,
    keyboardType: KeyboardType,
    onTextChange: (text: String) -> Unit
) {
    var textState by remember { mutableStateOf(TextFieldValue(text, TextRange(text.length))) }
    TextField(
        modifier = Modifier.padding(start = 50.dp, top = 5.dp, bottom = 5.dp),
        keyboardOptions = KeyboardOptions(keyboardType = keyboardType),
        value = textState,
        onValueChange = {
            val newText = if (keyboardType == KeyboardType.Number) {
                val newTextWithoutAnythingNonNumber = it.text.replace(oldValue = ",", newValue = "")
                    .replace(oldValue = "-", newValue = "")
                    .replace(oldValue = " ", newValue = "")
                if (label == "URL") {
                    Regex(pattern = "\\.+").replace(
                        newTextWithoutAnythingNonNumber,
                        replacement = "."
                    )
                } else {
                    newTextWithoutAnythingNonNumber.replace(oldValue = ".", newValue = "")
                }
            } else { it.text.replace(oldValue = " ", newValue = "") }
            textState = TextFieldValue(newText, TextRange(newText.length))
            onTextChange(newText)
        },
        label = { Text(label) },
        singleLine = true
    )
}