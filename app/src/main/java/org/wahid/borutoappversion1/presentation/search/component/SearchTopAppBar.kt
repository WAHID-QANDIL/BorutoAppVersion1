package org.wahid.borutoappversion1.presentation.search.component

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.tooling.preview.Preview
import org.wahid.borutoappversion1.R
import org.wahid.borutoappversion1.ui.theme.SEARCH_TOP_BAR_CORNER_SIZE
import org.wahid.borutoappversion1.ui.theme.SEARCH_TOP_BAR_HEIGHT
import org.wahid.borutoappversion1.ui.theme.topAppBarContainerColor
import org.wahid.borutoappversion1.ui.theme.topAppBarTitleColor
import org.wahid.borutoappversion1.utils.Constants.MEDIUM_ALPHA


@Composable
fun SearchTopAppBar(
    modifier: Modifier = Modifier,
    text: String,
    onTextChanged: (String) -> Unit,
    onSearchClicked: (String) -> Unit,
    onCloseClicked: () -> Unit,
) {
    SearchWidget(
        modifier = modifier,
        text = text,
        onTextChanged = onTextChanged,
        onSearchClicked = onSearchClicked,
        onCloseClicked = onCloseClicked
    )

}


@Composable
fun SearchWidget(
    modifier: Modifier = Modifier,
    text: String,
    onTextChanged: (String) -> Unit,
    onSearchClicked: (String) -> Unit,
    onCloseClicked: () -> Unit,
) {
    Surface(
        modifier = modifier
            .fillMaxWidth()
            .height(SEARCH_TOP_BAR_HEIGHT),

//        color = MaterialTheme.colorScheme.topAppBarContainerColor
    ) {
        TextField(
            modifier = Modifier.fillMaxWidth(),
            value = text,
            placeholder = {
                Text(
                    text = stringResource(R.string.search_here),
                    modifier = Modifier.alpha(MEDIUM_ALPHA),
                )
            },
            onValueChange = { onTextChanged(it) },
            textStyle = TextStyle(color = MaterialTheme.colorScheme.topAppBarTitleColor),
            singleLine = true,

            leadingIcon = {
                IconButton(
                    modifier = Modifier.alpha(MEDIUM_ALPHA),
                    onClick = {}
                ) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = stringResource(R.string.search_here)
                    )
                }
            },
            trailingIcon = {
                IconButton(
                    modifier = Modifier.alpha(MEDIUM_ALPHA),
                    onClick = {
                        if (text.isNotEmpty()) onTextChanged("") else onCloseClicked()
                    }
                ) {
                    Icon(
                        imageVector = Icons.Default.Close,
                        contentDescription = stringResource(R.string.close)
                    )
                }
            },
            keyboardOptions = KeyboardOptions(
                imeAction = ImeAction.Search
            ),
            keyboardActions = KeyboardActions(
                onSearch = { onSearchClicked(text) }
            ),
            colors = TextFieldDefaults.colors(
                cursorColor = MaterialTheme.colorScheme.topAppBarTitleColor,
                focusedContainerColor = MaterialTheme.colorScheme.topAppBarContainerColor,
                focusedLeadingIconColor = Color.White,
                focusedTrailingIconColor = Color.White,
                focusedTextColor = Color.White,
                focusedLabelColor = Color.White,
                focusedPlaceholderColor = Color.White,
                disabledPlaceholderColor = Color.Transparent,
                disabledContainerColor = Color.Transparent
            ),
            maxLines = 1,
            label = { Text(stringResource(R.string.search)) },
            shape = RoundedCornerShape(corner = CornerSize(SEARCH_TOP_BAR_CORNER_SIZE))


        )

    }
}

@Preview(showBackground = true)
@Composable
private fun SearchWidgetPreview() {
    SearchWidget(
        text = "sss",
        onTextChanged = {},
        onSearchClicked = {}
    ) { }
}