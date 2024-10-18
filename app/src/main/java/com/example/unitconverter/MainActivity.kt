 package com.example.unitconverter

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.Button
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.modifier.ModifierLocalProvider
import androidx.compose.ui.modifier.modifierLocalMapOf
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.unitconverter.ui.theme.UnitConverterTheme
import java.time.format.TextStyle
import kotlin.math.roundToInt

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            UnitConverterTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    UnitConverter()
                }
            }
        }
    }
}

@Composable
fun UnitConverter() {

    var inputValue by remember { mutableStateOf("") }
    var outputValue by remember { mutableStateOf("") }
    var inputUnit by remember { mutableStateOf("centimeter")}
    var outputUnit by remember{ mutableStateOf("Meters")}
    var iExpanded by remember { mutableStateOf(false)}
    var oExoanded by remember { mutableStateOf(false)}
    var conversionFactor = remember{ mutableStateOf(1.00)}
    var oconversionFactor = remember{ mutableStateOf(1.00)}


    fun converUnits() {

        // ?: - elvis operator
        val inputValueDouble = inputValue.toDoubleOrNull() ?: 0.0

        val result = (inputValueDouble * conversionFactor.value * 100.0/oconversionFactor.value).roundToInt() / 100.0
        outputValue = result.toString()
    }
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally

    ) {
        // Here all the UI elements will be stacked below each other
        Text("Unit Converter", style = MaterialTheme.typography.headlineLarge )
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(value = inputValue, onValueChange = {
            inputValue = it
            // Here goes what should happen , when the value of our OutlinedTextField Changes
        },
        label = { Text(text = "Enter Value")} )
        Spacer(modifier = Modifier.height(13.dp))

    Row {
        // Here all the UI elements will be stacked next to each other
        Box {
            //Input Button
           Button(onClick = { iExpanded = true}) {
               Text(text = inputUnit)
               Icon(Icons.Default.ArrowDropDown, contentDescription = "Arrow Down ")

           }
            DropdownMenu(expanded =iExpanded  , onDismissRequest = {iExpanded=false}) {
                DropdownMenuItem(text = { Text("Centimeters ")},
                    onClick = {
                        iExpanded=false
                        inputUnit="Centimeters"
                        conversionFactor.value =0.01
                        converUnits()
                    })
                DropdownMenuItem(text = { Text("Meters ")},
                    onClick = {  iExpanded=false
                        inputUnit="Meters"
                        conversionFactor.value =1.0
                        converUnits()
                    })
                DropdownMenuItem(text = { Text("Feet ")},
                    onClick = {
                        iExpanded=false
                        inputUnit="Feet"
                        conversionFactor.value =0.3048
                        converUnits() })
                DropdownMenuItem(text = { Text("Milimeters  ")},
                    onClick = {
                        iExpanded=false
                        inputUnit="Multimeter"
                        conversionFactor.value =0.001
                        converUnits()
                    })

            }

        }
        Spacer(modifier = Modifier.width(16.dp))
        Box {
            Button(onClick = { oExoanded = true }) {
                Text(text = outputUnit)
                Icon(Icons.Default.ArrowDropDown, contentDescription = "Arrow Down ")
            }
            DropdownMenu(expanded =oExoanded  , onDismissRequest = { oExoanded=false }) {
                DropdownMenuItem(text = { Text("Centimeters ")},
                    onClick = {  oExoanded=false
                        outputUnit="Multimeter"
                        oconversionFactor.value =0.01
                        converUnits() })
                DropdownMenuItem(text = { Text("Meters ")},
                    onClick = {
                        oExoanded=false
                        outputUnit="Meters"
                        oconversionFactor.value =1.0
                        converUnits() })
                DropdownMenuItem(text = { Text("Feet ")},
                    onClick = {
                        oExoanded=false
                        outputUnit="Feet"
                        oconversionFactor.value =0.3048
                        converUnits() })
                DropdownMenuItem(text = { Text("Milimeters  ")},
                    onClick = {
                        oExoanded=false
                        outputUnit="Milimeters"
                        oconversionFactor.value =0.001
                        converUnits()
                    })

            }
        }
    }
        Spacer(modifier = Modifier.height(16.dp))
        // Result text
        Text(text = "Result: $outputValue $outputUnit ",
            style = MaterialTheme.typography.headlineMedium
            )


    
}
}





 @Preview(showBackground = true)
 @Composable
 fun UnitConvertedPreview() {
     UnitConverter()
 }