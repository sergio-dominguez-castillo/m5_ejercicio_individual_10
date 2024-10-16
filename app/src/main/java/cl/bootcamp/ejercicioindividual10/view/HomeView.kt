package cl.bootcamp.ejercicioindividual10.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MultiChoiceSegmentedButtonRow
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SegmentedButton
import androidx.compose.material3.SegmentedButtonDefaults.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.Alignment
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cl.bootcamp.ejercicioindividual10.components.MainTextField
import cl.bootcamp.ejercicioindividual10.components.Space
import androidx.compose.material3.SegmentedButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import cl.bootcamp.ejercicioindividual10.components.MainButton


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeView() {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text(text = "Ejercicio Individual 10", color = Color.White) },
                colors = TopAppBarDefaults.topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary
                )
            )
        }
    ) {
        ContentHomeView(it)
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ContentHomeView(
    paddingValues: PaddingValues
) {

    var age by remember { mutableStateOf("") }
    var weight by remember { mutableStateOf("") }
    var height by remember { mutableStateOf("") }
    var bmi by remember { mutableStateOf("") }
    var checkedList = remember { mutableStateListOf<Int>() }
    val options = listOf("Hombre", "Mujer")


    Column(
        modifier = Modifier
            .padding(paddingValues)
            .padding(10.dp)
            .fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            text = "Calculadora de IMC",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )
        Space()

        MultiChoiceSegmentedButtonRow {
            options.forEachIndexed { index, label ->
                SegmentedButton(
                    shape = SegmentedButtonDefaults.itemShape(index = index, count = options.size),
                    /*icon = {
                        SegmentedButtonDefaults.Icon(active = index in checkedList) {
                            Icon(
                                imageVector = icons[index],
                                contentDescription = null,
                                modifier = Modifier.size(SegmentedButtonDefaults.IconSize)
                            )
                        }
                    },*/
                    onCheckedChange = {

                    for (i in 0..options.size) {checkedList.remove(i)}
                    checkedList.add(index)

                    },
                    checked = index in checkedList
                ) {
                    Text(label)
                }
            }
        }

        Space()
        MainTextField(
            value = age,
            onValueChange = { age = it },
            label = "Edad"
        )
        Space()
        MainTextField(
            value = weight,
            onValueChange = { weight = it },
            label = "Peso (kg)"
        )
        Space()
        MainTextField(
            value = height,
            onValueChange = { height = it },
            label = "Altura (cm)"
        )
        Space()
        MainButton(
            text = "Calcular"
        ) {
            // Calcular el IMC
            bmi="0.0"
            if (height != "" && weight != "") {
                bmi= calculateBMI(weight.toDouble(), height.toDouble()).toString()
            }

        }

        Space()
        Text(
            text = "$bmi",
            fontSize = 48.sp,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(bottom = 16.dp)
        )
    }
}

fun calculateBMI(weightKG: Double, heightCM: Double): Double {
    val heightM = heightCM/100  // transformo de cm a mt
    val result = weightKG / (heightM * heightM)
    return kotlin.math.round(result * 100) / 100.0
}