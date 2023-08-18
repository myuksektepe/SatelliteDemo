package satellite.demo.presentation

import android.content.Context
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.launch
import satellite.demo.core.Resource
import satellite.demo.core.base.BaseError
import satellite.demo.core.base.BaseViewModel
import satellite.demo.data.DataRepository
import satellite.demo.data.DataRepositoryImpl
import satellite.demo.data.LocalDataSourceImpl
import satellite.demo.domain.FlowState
import satellite.demo.domain.models.Satellite
import satellite.demo.presentation.usecase.SatelliteUseCase
import javax.inject.Inject


/**
 * Created by Murat Yüksektepe on 18.08.2023.
 * muratyuksektepe.com
 * yuksektepemurat@gmail.com
 */

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val satelliteUseCase: SatelliteUseCase
) : BaseViewModel() {

    private val satelliteListFlow = MutableStateFlow<Resource<List<Satellite>>>(Resource.Loading)
    val satelliteList: StateFlow<Resource<List<Satellite>>> = satelliteListFlow

    fun getSatelliteList() {
        viewModelScope.launch(Dispatchers.IO) {
            satelliteUseCase.invoke(Unit).collectLatest {
                satelliteListFlow.value = it
            }
        }
    }
}