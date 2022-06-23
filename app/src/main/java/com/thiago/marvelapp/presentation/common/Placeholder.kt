package com.thiago.marvelapp.presentation.common

import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.graphics.Shape
import com.google.accompanist.placeholder.PlaceholderHighlight
import com.google.accompanist.placeholder.material.placeholder
import com.google.accompanist.placeholder.material.shimmer

fun Modifier.appPlaceholder(shape: Shape? = null) = composed {
    this.placeholder(
        visible = true,
        highlight = PlaceholderHighlight.shimmer(),
        shape = shape
    )
}