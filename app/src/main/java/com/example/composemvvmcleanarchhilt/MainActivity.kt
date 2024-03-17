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
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

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
 *  Dependencies :
 *                  ConverterViewModelFactory(private val repository: ConverterRepository)
 *                  class ConverterViewModel(private val repository: ConverterRepository)
 *                  class ConverterRepositoryImpl(private val dao : ConverterDAO)
 *
 *                  val dao = ConverterDatabase.getInstance(application).converterDAO
 *                  val repository = ConverterRepositoryImpl(dao)
 *                  val factory = ConverterViewModelFactory(repository)
 *
 *  So Finally we need below dependencies:
 *                  ConverterViewModelFactory
 *                  ConverterRepository
 *                  ConverterDatabase
 */

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var factory : ConverterViewModelFactory

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        /*
            // We are removing it with Hilt Dependency Injection

        val dao = ConverterDatabase.getInstance(application).converterDAO
        val repository = ConverterRepositoryImpl(dao)
        val factory = ConverterViewModelFactory(repository)

         */

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

