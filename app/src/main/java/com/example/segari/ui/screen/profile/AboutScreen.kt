package com.example.segari.ui.screen.profile

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.segari.R
import com.example.segari.ui.theme.Orange
import com.example.segari.ui.theme.SegariTheme

@Composable
fun AboutScreen(
    modifier: Modifier = Modifier,
) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = modifier
            .fillMaxSize()
    ) {
        Box(modifier = Modifier){
            Image(
                painter = painterResource(R.drawable.background_profile),
                contentDescription = "Banner Image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(160.dp)
                    .fillMaxWidth()
            )
            Image(
                painter = painterResource(R.drawable.about_photo),
                contentDescription = stringResource(R.string.about_photo),
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(200.dp)
                    .padding(top = 16.dp, start = 8.dp)
                    .clip(CircleShape)
            )
        }
        Text(
            text = stringResource(R.string.nama),
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.ExtraBold
            ),
            fontSize = 20.sp,
            color = Orange,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(top = 32.dp, bottom = 32.dp)

        )
        Row(
            modifier = Modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically)
        {
            Image(
                painter = painterResource(R.drawable.gmail),
                contentDescription = stringResource(R.string.gmail),
                modifier = Modifier
                    .height(20.dp)
                    .width(20.dp)  // Ubah lebar gambar sesuai kebutuhan
                    .fillMaxWidth()
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = stringResource(R.string.email),
                style = MaterialTheme.typography.bodyMedium,
                fontSize = 18.sp,
                textAlign = TextAlign.Center,
            )
        }
        Row(
            modifier = Modifier.padding(8.dp),
            verticalAlignment = Alignment.CenterVertically)
        {
            Image(
                painter = painterResource(R.drawable.linkedin),
                contentDescription = stringResource(R.string.linkedin),
                modifier = Modifier
                    .height(20.dp)
                    .width(20.dp)  // Ubah lebar gambar sesuai kebutuhan
                    .fillMaxWidth()
            )
            Spacer(modifier = Modifier.width(8.dp))
            Text(
                text = stringResource(R.string.linkedin),
                style = MaterialTheme.typography.bodyMedium,
                fontSize = 18.sp,
                textAlign = TextAlign.Center,
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ProfileScreenPreview() {
    SegariTheme {
        AboutScreen()
    }
}