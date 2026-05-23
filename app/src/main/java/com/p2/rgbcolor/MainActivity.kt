package com.p2.rgbcolor

import android.os.Bundle
import android.view.Gravity
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.graphics.toColorInt
import com.p2.rgbcolor.ui.theme.RGBColorTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            RGBColorTheme {
                var redChannel by remember { mutableStateOf("") }
                var greenChannel by remember { mutableStateOf("") }
                var blueChannel by remember { mutableStateOf("") }
                var fullRGB by remember { mutableStateOf("") }

                val instructionsMessage =
                    stringResource(id = R.string.instructions)
                val enterCompatibleHexErrorMessage =
                    stringResource(id = R.string.hexErrorMessage)
                val whiteDefault =
                    stringResource(id = R.string.whiteDefault)




                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(innerPadding)
                            .padding(16.dp),
                        horizontalAlignment =
                            Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(16.dp)
                    ) {
                        Text(stringResource(R.string.instructions))

                        OutlinedTextField(
                            modifier = Modifier.fillMaxWidth(),
                            value = redChannel,
                            onValueChange = {redChannel = it},
                            label = {
                                Text(
                                    text = stringResource(
                                        id = R.string.redChannel
                                    )
                                )
                            }
                        )
                        OutlinedTextField(
                            modifier = Modifier.fillMaxWidth(),
                            value = greenChannel,
                            onValueChange = {greenChannel = it},
                            label = {
                                Text(
                                    text = stringResource(
                                        id = R.string.greenChannel
                                    )
                                )
                            }
                        )
                        OutlinedTextField(
                            modifier = Modifier.fillMaxWidth(),
                            value = blueChannel,
                            onValueChange = {blueChannel = it},
                            label = {
                                Text(
                                    text = stringResource(
                                        id = R.string.blueChannel
                                    )
                                )
                            }
                        )
                        val context = LocalContext.current
                        Button(
                            modifier = Modifier.fillMaxWidth(),
                            onClick = {
                                if (
                                    isValidHexInput(redChannel) &&
                                    isValidHexInput(greenChannel) &&
                                    isValidHexInput(blueChannel))
                                    fullRGB = "#$redChannel$greenChannel$blueChannel"
                                else {
                                    fullRGB = whiteDefault
                                    val toast = Toast.makeText(
                                        context,
                                        enterCompatibleHexErrorMessage,
                                        Toast.LENGTH_LONG


                                    )
                                    toast.setGravity(Gravity.CENTER,0,0)
                                    toast.show()
                                }


                            }
                            
                        ){
                            Text(stringResource(R.string.createColor))
                        }
                        if(fullRGB.isNotBlank()) {
                            Text(
                                modifier = Modifier
                                    .background(Color(fullRGB.toColorInt()))
                                    .padding(24.dp),
                                text = stringResource(
                                    R.string.displayText
                                )
                            )
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

fun isValidHexInput(input: String): Boolean {
    return input.filter {
        it in '0'..'9' ||
                it in 'A'..'F' ||
                it in 'a'..'f'
    }.length == 2
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    RGBColorTheme {
        Greeting("Android")
    }
}