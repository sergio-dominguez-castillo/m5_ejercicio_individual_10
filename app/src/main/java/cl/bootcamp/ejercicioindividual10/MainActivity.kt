package cl.bootcamp.ejercicioindividual10

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import cl.bootcamp.ejercicioindividual10.ui.theme.EjercicioIndividual10Theme
import cl.bootcamp.ejercicioindividual10.view.HomeView

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            EjercicioIndividual10Theme {
                HomeView()
            }
        }
    }
}

