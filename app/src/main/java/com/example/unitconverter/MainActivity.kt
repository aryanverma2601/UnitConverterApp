package com.example.unitconverter

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.unitconverter.ui.theme.CustomBlue
import com.example.unitconverter.ui.theme.Purple40
import com.example.unitconverter.ui.theme.UnitConverterTheme
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

    var inputvalue by remember { mutableStateOf("") }
    var outputvalue by remember { mutableStateOf("") }
    var inputunit by remember { mutableStateOf("Select") }
    var outputunit by remember { mutableStateOf("Select") }
    var oc by remember { mutableStateOf("") }
    var iExpanded by remember { mutableStateOf(false) }
    var oExpanded by remember { mutableStateOf(false) }
    val conversionFactor = remember { mutableStateOf(1.00) }
    val oConversionFactor = remember { mutableStateOf(1.00) }


    fun conversionunits(){
        val inputValueDouble = inputvalue.toDoubleOrNull() ?: 0.0
        val result = (inputValueDouble * conversionFactor.value * 100.0 / oConversionFactor.value).roundToInt() /100.0
        outputvalue = result.toString()
    }

    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ){
        Text(
            text = "Unit Converter",
            style= MaterialTheme.typography.headlineLarge,
            color= Color.White,
            textAlign = TextAlign.Center,
            fontSize = 50.sp,

            modifier= Modifier.background(Purple40).fillMaxWidth()
        )
    }



    Column (
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ){

        Spacer(modifier = Modifier.height(16.dp))

        //All UI will stacked below each other
        Spacer(modifier = Modifier.height(16.dp))
        OutlinedTextField(
            value = inputvalue, onValueChange = {
                inputvalue = it
                conversionunits()
            //Here what happens when the text value of our outlineTextlin
            },
            label = {Text(text = "Enter Value")})
        Spacer(modifier = Modifier.height(16.dp))
        Row {
            //INPUT
          Box{
              Button(onClick = { iExpanded =true }) {
                  Text(text = inputunit)
                  Icon(Icons.Default.ArrowDropDown ,
                      contentDescription = "Arrow Down")
              }
              DropdownMenu(expanded = iExpanded, onDismissRequest = { iExpanded = false }) {
                  DropdownMenuItem(text = { Text(text = "Centimeters") }, onClick = {
                      iExpanded = false
                      inputunit = "Centimeters"
                      conversionFactor.value = 0.01
                      conversionunits()
                  })
                  DropdownMenuItem(text = { Text(text = "Meters") }, onClick = {
                      iExpanded = false
                      inputunit = "Meters"
                      conversionFactor.value = 1.0
                      conversionunits()
                  })
                  DropdownMenuItem(text = { Text(text = "Feet") }, onClick = {
                      iExpanded = false
                      inputunit = "Feet"
                      conversionFactor.value = 0.3048
                      conversionunits()
                  })
                  DropdownMenuItem(text = { Text(text = "Milimeters") }, onClick = {
                      iExpanded = false
                      inputunit = "Milimeters"
                      conversionFactor.value = 0.001
                      conversionunits()
                  })
                  
              }
          }
            Spacer(modifier = Modifier.width(16.dp))
            //Output
            Box{
                Button(onClick = { oExpanded = true }) {
                    Text(text = outputunit)
                    Icon(Icons.Default.ArrowDropDown ,
                        contentDescription = "Arrow Down")
                }
                DropdownMenu(expanded = oExpanded, onDismissRequest = { oExpanded = false }) {
                    DropdownMenuItem(text = { Text(text = "Centimeters") }, onClick = {
                        oExpanded = false
                        outputunit = "Centimeters"
                        oc = outputunit
                        oConversionFactor.value = 0.01
                        conversionunits()
                    })
                    DropdownMenuItem(text = { Text(text = "Meters") }, onClick = {
                        oExpanded = false
                        outputunit = "Meters"
                        oc = outputunit
                        oConversionFactor.value = 1.00
                        conversionunits()
                    })
                    DropdownMenuItem(text = { Text(text = "Feet") }, onClick = {
                        oExpanded = false
                        outputunit = "Feet"
                        oc = outputunit
                        oConversionFactor.value = 0.3048
                        conversionunits()
                    })
                    DropdownMenuItem(text = { Text(text = "Milimeters") }, onClick = {
                        oExpanded = false
                        outputunit = "Milimeters"
                        oc = outputunit
                        oConversionFactor.value = 0.001
                        conversionunits() })

                }
            }
        }
        Spacer(modifier = Modifier.height(16.dp))
        // Result
        Text(text = "Result $outputvalue $oc" ,
            style = MaterialTheme.typography.headlineLarge)
    }
}
    @Preview(showBackground = true, showSystemUi = true)
    @Composable
    fun UnitConverterPreview() {
        UnitConverter()
    }

