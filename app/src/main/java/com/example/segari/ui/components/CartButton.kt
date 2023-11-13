package com.example.segari.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.segari.ui.theme.Green
import com.example.segari.ui.theme.SegariTheme
import com.example.segari.ui.theme.White

@Composable
fun CartButton(
    text: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    onClick: () -> Unit,
) {
    Button(
        onClick = onClick,
        enabled = enabled,
        modifier = modifier
            .fillMaxWidth().height(52.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Green,
        contentColor = White
    )
    ) {
        Text(
            text = text,
            modifier = Modifier.align(Alignment.CenterVertically)
        )
    }
}

@Composable
@Preview(showBackground = true)
fun OrderButtonPreview() {
    SegariTheme {
        CartButton(
            text = "Tambah ke keranjang",
            onClick = {}
        )
    }
}