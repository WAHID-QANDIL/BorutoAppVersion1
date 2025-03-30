package org.wahid.borutoappversion1.presentation.screens.details.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import org.wahid.borutoappversion1.ui.theme.SMALL_PADDING
import org.wahid.borutoappversion1.ui.theme.titleColor

@Composable
fun OrderedList(
    items: List<String>,
    textColor: Color,
    title: String,
) {

    Column {
        Text(
            text = title,
            color = textColor,
            fontSize = MaterialTheme.typography.bodyLarge.fontSize,
            fontWeight = FontWeight.Medium
        )
        Spacer(modifier = Modifier.height(SMALL_PADDING))

        items.filter { it.length > 2 }.forEachIndexed { index, item ->
            Text(
                text = "${index + 1}. $item",
                color = textColor,
                fontSize = MaterialTheme.typography.bodySmall.fontSize,
            )
        }

    }

}

@Preview
@Composable
private fun OrderedListPreview() {
    OrderedList(
        items = listOf("item1", "item2"),
        textColor = MaterialTheme.colorScheme.titleColor,
        title = "Family"
    )
}