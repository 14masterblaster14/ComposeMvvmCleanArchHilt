package com.example.composemvvmcleanarchhilt

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import com.example.composemvvmcleanarchhilt.compose.BaseScreen
import com.example.composemvvmcleanarchhilt.data.ConverterDatabase
import com.example.composemvvmcleanarchhilt.data.ConverterRepositoryImpl
import com.example.composemvvmcleanarchhilt.ui.theme.ComposeMvvmCleanArchHiltTheme

/**
 *  Parent Composable - BaseScreen
 *      ->  Upper Part - TopScreen
 *              -> Conversion Menu
 *              -> InputBlock
 *              -> Result Block
 *      ->  HistoryScreen
 *              -> Text
 *              -> Button
 *              -> ConversionHistoryList
 *
 *       We should access and call ViewModel instances at screen-level composables.
 *       i.e. close to a root composable from an Activity,fragment, or destination of a Navigation graph.
 *
 *      We should never pass down the ViewModel instances to other composables.
 *      We pass only the data they need and functions that perform the required logic as parameter.
 *
 *
 *
 *
 */

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val dao = ConverterDatabase.getInstance(application).converterDAO
        val repository = ConverterRepositoryImpl(dao)
        val factory = ConverterViewModelFactory(repository)

        setContent {
            ComposeMvvmCleanArchHiltTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    BaseScreen(factory = factory)

                }
            }
        }
    }
}

