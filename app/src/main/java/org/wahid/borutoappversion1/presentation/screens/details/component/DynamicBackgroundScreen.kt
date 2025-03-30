package org.wahid.borutoappversion1.presentation.screens.details.component

//import android.graphics.drawable.BitmapDrawable
//import androidx.compose.foundation.background
//import androidx.compose.foundation.layout.Box
//import androidx.compose.foundation.layout.fillMaxSize
//import androidx.compose.runtime.Composable
//import androidx.compose.runtime.getValue
//import androidx.compose.runtime.mutableStateOf
//import androidx.compose.runtime.remember
//import androidx.compose.runtime.setValue
//import androidx.compose.ui.Modifier
//import androidx.compose.ui.graphics.Color
//import androidx.compose.ui.layout.ContentScale
//import androidx.compose.ui.platform.LocalContext
//import androidx.palette.graphics.Palette
//import coil3.compose.AsyncImage
//import coil3.request.ImageRequest
//import coil3.request.crossfade
//
//@Composable
//fun DynamicBackgroundScreen(imageUrl: String) {
//    // Initial background color while image loads.
//    var dominantColor by remember { mutableStateOf(Color.LightGray) }
//    val context = LocalContext.current
//
//    Box(
//        modifier = Modifier
//            .fillMaxSize()
//            .background(dominantColor)
//    ) {
//        AsyncImage(
//            model = ImageRequest.Builder(context)
//                .data(imageUrl)
//                .crossfade(true)
//                // The target callback provides the Drawable result
//                .target { drawable ->
//                    // Extract bitmap from the drawable if possible
//                    val bitmap = (drawable as? BitmapDrawable)?.bitmap
//                    bitmap?.let {
//                        // Generate a Palette instance asynchronously.
//                        Palette.from(it).generate { palette ->
//                            // You can change this to a different swatch if desired.
//                            palette?.vibrantSwatch?.rgb?.let { colorValue ->
//                                dominantColor = Color(colorValue)
//                            }
//                        }
//                    }
//                }
//                .build(),
//            contentDescription = "Loaded image",
//            contentScale = ContentScale.Crop,
//            modifier = Modifier.fillMaxSize()
//        )
//    }
//}