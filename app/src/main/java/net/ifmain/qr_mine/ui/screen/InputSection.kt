package net.ifmain.qr_mine.ui.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import net.ifmain.qr_mine.ui.theme.SkyBlue
import net.ifmain.qr_mine.ui.theme.SoftIvory

@Composable
fun InputSection(userURL: String, onUserURLChange: (String) -> Unit, onSubmit: () -> Unit) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        OutlinedTextField(
            value = userURL,
            onValueChange = onUserURLChange,
            label = {
                Text(
                    text = "URL을 입력하세요.",
                )
            },
            shape = RoundedCornerShape(12.dp),
            modifier = Modifier.weight(1f),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = SkyBlue,
                unfocusedBorderColor = SkyBlue,
                cursorColor = SkyBlue,
                focusedLabelColor = SkyBlue,
                unfocusedLabelColor = Color.LightGray,
            ),
            textStyle = TextStyle(color = SoftIvory)
        )
        Button(
            onClick = onSubmit,
            modifier = Modifier.size(80.dp, 48.dp),
            colors = ButtonDefaults.buttonColors(containerColor = SkyBlue, contentColor = SoftIvory)
        ) {
            Text(
                text = "생성",
                style = MaterialTheme.typography.bodySmall,
            )
        }
    }
}