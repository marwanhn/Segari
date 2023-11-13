package com.example.segari.ui.screen.home

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.segari.R
import com.example.segari.di.Injection
import com.example.segari.model.OrderItem
import com.example.segari.ui.common.UiState
import com.example.segari.ui.components.ProductItem
import com.example.segari.ui.components.SearchBar
import com.example.segari.ui.screen.ViewModelFactory
import com.example.segari.ui.theme.Green


@Composable

fun HomeScreen(
    modifier: Modifier = Modifier,
    viewModel: HomeViewModel = viewModel(
        factory = ViewModelFactory(Injection.provideRepository())
    ),
    navigateToDetail: (Long) -> Unit,
) {
    val query by viewModel.query
    Scaffold { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
//                .verticalScroll(rememberScrollState())
        ) {
            Box(modifier = modifier) {
                Image(
                    painter = painterResource(R.drawable.banner1),
                    contentDescription = "Banner Image",
                    contentScale = ContentScale.Crop,
                    modifier = Modifier
                        .height(160.dp)
                        .fillMaxWidth()

                )
                SearchBar(
                    query = query,
                    onQueryChange = { viewModel.search(it) },
                    onSearch = { viewModel.search(query) },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .heightIn(min = 48.dp)
                )
            }
            Text(
                text = stringResource(R.string.produk_pilihan),
                style = MaterialTheme.typography.headlineSmall.copy(
                    fontWeight = FontWeight.ExtraBold,
                    color = Green
                ),
                modifier = modifier
                    .padding(16.dp)

            )
            viewModel.uiState.collectAsState(initial = UiState.Loading).value.let { uiState ->
                when (uiState) {
                    is UiState.Loading -> {
                        viewModel.getAllProducts()
                    }

                    is UiState.Success -> {
                        HomeContent(
                            orderItem = uiState.data,
                            navigateToDetail = navigateToDetail,
                            modifier = modifier
                                .fillMaxWidth()
                        )
                    }

                    is UiState.Error -> {}
                }
            }
        }

    }


}

@Composable
fun HomeContent(
    orderItem: List<OrderItem>,
    navigateToDetail: (Long) -> Unit,
    modifier: Modifier = Modifier,

    ) {

    LazyVerticalGrid(
        columns = GridCells.Adaptive(160.dp),
        contentPadding = PaddingValues(16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        verticalArrangement = Arrangement.spacedBy(16.dp),
        modifier = modifier
            .fillMaxWidth()
            .padding(vertical = 10.dp)
    ) {
        items(orderItem) {
            ProductItem(
                image = it.item.image,
                title = it.item.title,
                weight = it.item.weight,
                price = it.item.price,
                modifier = Modifier.clickable {
                    navigateToDetail(it.item.id)
                }
            )
        }
    }
}

