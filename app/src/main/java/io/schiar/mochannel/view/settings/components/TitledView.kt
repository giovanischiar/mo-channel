package io.schiar.mochannel.view.settings.components

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.tv.material3.ExperimentalTvMaterial3Api
import androidx.tv.material3.MaterialTheme
import androidx.tv.material3.Text
import io.schiar.mochannel.R

@OptIn(ExperimentalTvMaterial3Api::class)
@Composable
fun TitledView(modifier: Modifier = Modifier, title: String, content: @Composable () -> Unit) {
    Column(modifier = modifier.fillMaxWidth()) {
        Box(modifier = Modifier.padding(start = 50.dp, bottom = 40.dp)) {
            Text(
                text = title,
                style = MaterialTheme.typography.headlineLarge,
                color = colorResource(R.color.moonColor)
            )
        }
        content()
    }
}