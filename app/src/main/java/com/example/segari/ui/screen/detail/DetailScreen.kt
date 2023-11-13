package com.example.segari.ui.screen.detail

import androidx.annotation.DrawableRes
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.segari.R
import com.example.segari.di.Injection
import com.example.segari.ui.common.UiState
import com.example.segari.ui.components.CartButton
import com.example.segari.ui.components.ProductCounter
import com.example.segari.ui.screen.ViewModelFactory
import com.example.segari.ui.theme.Orange
import com.example.segari.ui.theme.SegariTheme
import com.example.segari.ui.theme.White

@Composable
fun DetailScreen(
    productId: Long, viewModel: DetailViewModel = viewModel(
        factory = ViewModelFactory(
            Injection.provideRepository()
        )
    ), navigateBack: () -> Unit, navigateToCart: () -> Unit
) {
    viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
        when (uiState) {
            is UiState.Loading -> {
                viewModel.getProductById(productId)
            }

            is UiState.Success -> {
                val data = uiState.data
                DetailContent(data.item.image,
                    data.item.title,
                    data.item.price,
                    data.item.weight,
                    data.item.description,
                    data.count,
                    onBackClick = navigateBack,
                    onAddToCart = { count ->
                        viewModel.addToCart(data.item, count)
                        navigateToCart()
                    })
            }

            is UiState.Error -> {}
        }
    }
}

@Composable
fun DetailContent(
    @DrawableRes image: Int,
    title: String,
    basePrice: Int,
    weight: String,
    description: String,
    count: Int,
    onBackClick: () -> Unit,
    onAddToCart: (count: Int) -> Unit,
    modifier: Modifier = Modifier,
) {

    var totalPrice by rememberSaveable { mutableStateOf(0) }
    var orderCount by rememberSaveable { mutableStateOf(count) }

    Column(modifier = modifier) {
        Column(
            modifier = Modifier
                .verticalScroll(rememberScrollState())
                .weight(1f)
        ) {
            Box {
                Image(
                    painter = painterResource(image),
                    contentDescription = null,
                    contentScale = ContentScale.Crop,
                    modifier = modifier
                        .height(400.dp)
                        .fillMaxWidth()
                        .clip(RoundedCornerShape(bottomStart = 20.dp, bottomEnd = 20.dp))
                )
                Icon(imageVector = Icons.Default.ArrowBack,
                    contentDescription = stringResource(R.string.back),

                    modifier = Modifier
                        .padding(16.dp)
                        .background(White, shape = CircleShape)
                        .clip(CircleShape)
                        .clickable { onBackClick() })
            }
            Column(
                horizontalAlignment = Alignment.Start, modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = stringResource(R.string.price, basePrice),
                    style = MaterialTheme.typography.titleMedium.copy(
                        fontWeight = FontWeight.ExtraBold
                    ),
                    color = Orange
                )
                Text(
                    text = title,
                    textAlign = TextAlign.Center,
                    style = MaterialTheme.typography.headlineSmall.copy(
                        fontWeight = FontWeight.ExtraBold
                    ),
                )
                Text(
                    text = weight,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.Normal
                    ),
                    color = MaterialTheme.colorScheme.secondary,
                    textAlign = TextAlign.Justify,
                )
            }
            Spacer(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(4.dp)
                    .background(Color.LightGray)
                    .padding(16.dp)
            )
            Column(
                horizontalAlignment = Alignment.Start, modifier = Modifier.padding(16.dp)
            ) {
                Text(
                    text = description,
                    style = MaterialTheme.typography.bodyMedium.copy(
                        fontWeight = FontWeight.Normal
                    ),
                    textAlign = TextAlign.Justify,
                )
            }

        }

        Spacer(
            modifier = Modifier
                .fillMaxWidth()
                .height(4.dp)
                .background(Color.LightGray)
        )
        Column(
            modifier = Modifier.padding(16.dp)
        ) {
            ProductCounter(
                1,
                orderCount,
                onProductIncreased = { orderCount++ },
                onProductDecreased = { if (orderCount > 0) orderCount-- },
                modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                    .padding(bottom = 16.dp)
            )
            totalPrice = (basePrice * orderCount)
            CartButton(text = stringResource(R.string.add_to_cart, totalPrice),
                enabled = orderCount > 0,
                onClick = {
                    onAddToCart(orderCount)
                })
        }
    }
}

@Preview(showBackground = true, device = Devices.PIXEL_4)
@Composable
fun DetailContentPreview() {
    SegariTheme {
        DetailContent(R.drawable.buah_1,
            "Buah Naga Merah",
            19_000,
            "0.9 - 1.1 kg / pack",
            "Informasi Asal:" + "\n" + "Buah Lokal." + "\n" + "Sebutan Umum:" + "\n" + "Red Dragon Fruit, Pitaya Roja, Red-Fleshed Pitaya, Hylocereus polyrhizus.",
            1,
            onBackClick = {},
            onAddToCart = {})
    }
}