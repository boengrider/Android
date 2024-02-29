package com.example.tempconverter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.VerticalAlignmentLine
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.toUpperCase
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.tempconverter.ui.theme.TempConverterTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            TempConverterTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    TempConverterApp()
                }
            }
        }
    }
}

enum class Direction {
    ToCelsius, ToFahrenheit
}
@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TempConverterInput(temperature: String, onValueChange: (String) -> Unit) {
    TextField(value = temperature, onValueChange = onValueChange)
}

@Composable
fun TempConverted(temperature: String, direction: Int) {


    val conversion = {
        val convertedTemperature =

        if (direction == R.string.fahrenheit && temperature != null) {
            (temperature.to * 1.8F) + 32F
        } else {
            (convertedTemperature - 32F) / 1.8F
        }

    }

    Text(text = convertedTemperature.toString())
}



@Composable
fun TempDirectionToggle(isSelected: Boolean, labelId: Int, onClick: () -> Unit) {
    Row(verticalAlignment = Alignment.CenterVertically) {
        RadioButton(selected = isSelected, onClick = onClick)
        Text(text = stringResource(id = labelId))
    }
}


@Composable
@Preview
fun TempConverterApp(modifier: Modifier = Modifier) {
    val temperature = remember { mutableStateOf("") }
    val direction = remember { mutableStateOf(R.string.celsius)}

    Column(modifier = modifier,
           horizontalAlignment = Alignment.CenterHorizontally) {
        TempConverterInput(temperature = temperature.value,
                           onValueChange = { temperature.value = it })

        TempConverted(temperature = temperature.value, direction = direction.value)

        Row {
            TempDirectionToggle(isSelected = direction.value == R.string.celsius,
                                labelId = R.string.celsius,
                                onClick = { direction.value = R.string.celsius })

            TempDirectionToggle(isSelected = direction.value == R.string.fahrenheit,
                                labelId = R.string.fahrenheit,
                                onClick = { direction.value = R.string.fahrenheit})
        }


    }




}