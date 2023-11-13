package com.example.segari.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.segari.R
import com.example.segari.ui.theme.Orange
import com.example.segari.ui.theme.SegariTheme
import com.example.segari.ui.theme.Shapes


@Composable
fun ProductItem(
    image: Int,
    title: String,
    weight: String,
    price: Int,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier = modifier
    ) {
        Image(
            painter = painterResource(image),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(170.dp)
                .clip(Shapes.medium)
        )
        Text(
            text = title,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.ExtraBold
            )
        )
        Text(
            text = weight,
            style = MaterialTheme.typography.titleSmall,
            color = MaterialTheme.colorScheme.secondary,
            modifier = Modifier
                .padding(bottom = 8.dp)

        )
        Text(
            text = stringResource(R.string.price, price),
            style = MaterialTheme.typography.titleSmall,
            color = Orange
        )
    }
}

@Composable
@Preview(showBackground = true)
fun ProductItemPreview() {
    SegariTheme {
        ProductItem(R.drawable.buah_1, "Buah Naga Merah", "0.9 - 1.1 kg / pack", 19_000)
    }
}