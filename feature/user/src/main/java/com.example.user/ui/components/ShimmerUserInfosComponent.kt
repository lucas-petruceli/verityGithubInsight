package com.example.user.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.unit.dp
import com.example.components.ShimmerItem

@Composable
fun ShimmerUserInfosComponent() {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        ShimmerItem { brush ->
            Box(
                modifier = Modifier
                    .height(200.dp)
                    .width(200.dp)
                    .clip(CircleShape)
                    .background(brush)
            )
        }

        Spacer(modifier = Modifier.height(16.dp))

        ShimmerItem { brush ->
            Box(
                modifier = Modifier
                    .height(24.dp)
                    .width(150.dp)
                    .background(brush, RoundedCornerShape(4.dp))
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Column(
            modifier = Modifier.fillMaxWidth(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            ShimmerItem { brush ->
                Box(
                    modifier = Modifier
                        .height(16.dp)
                        .width(100.dp)
                        .background(brush, RoundedCornerShape(4.dp))
                )
            }
            Spacer(modifier = Modifier.height(4.dp))

            Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.Center
            ) {
                ShimmerItem { brush ->
                    UserStatShimmer(brush)
                }
                Spacer(modifier = Modifier.width(24.dp))
                ShimmerItem { brush ->
                    UserStatShimmer(brush)
                }
            }
        }
    }
}

@Composable
private fun UserStatShimmer(brush: Brush) {
    Box(
        modifier = Modifier
            .height(16.dp)
            .width(50.dp)
            .background(brush, RoundedCornerShape(4.dp))
    )
}