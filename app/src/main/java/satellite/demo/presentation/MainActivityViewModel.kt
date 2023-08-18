package satellite.demo.presentation

import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import satellite.demo.core.Resource
import satellite.demo.core.base.BaseViewModel
import satellite.demo.domain.models.Satellite
import satellite.demo.domain.models.SatelliteDetail
import satellite.demo.domain.models.SatellitePosition
import satellite.demo.presentation.usecase.SatelliteDetailGetByIdUseCase
import satellite.demo.presentation.usecase.SatelliteGetByIdUseCase
import satellite.demo.presentation.usecase.SatelliteGetListUseCase
import satellite.demo.presentation.usecase.SatellitePositionsByIdUseCase
import javax.inject.Inject


/**
 * Created by Murat Yüksektepe on 18.08.2023.
 * muratyuksektepe.com
 * yuksektepemurat@gmail.com
 */

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    private val satelliteGetListUseCase: SatelliteGetListUseCase,
    private val satelliteGetByIdUseCase: SatelliteGetByIdUseCase,
    private val satelliteDetailGetByIdUseCase: SatelliteDetailGetByIdUseCase,
    private val satellitePositionsByIdUseCase: SatellitePositionsByIdUseCase
) : BaseViewModel() {

    private val satelliteListFlow = MutableStateFlow<Resource<List<Satellite>>>(Resource.Loading)
    val satelliteList: StateFlow<Resource<List<Satellite>>> = satelliteListFlow

    private val satelliteFlow = MutableStateFlow<Resource<Satellite>>(Resource.Loading)
    val satellite: StateFlow<Resource<Satellite>> = satelliteFlow

    private val satelliteDetailFlow = MutableStateFlow<Resource<SatelliteDetail>?>(null)
    val satelliteDetail: StateFlow<Resource<SatelliteDetail>?> = satelliteDetailFlow

    private val satellitePositionsFlow = MutableStateFlow<Resource<List<SatellitePosition>>>(Resource.Loading)
    val satellitePositions: StateFlow<Resource<List<SatellitePosition>>> = satellitePositionsFlow

    fun getSatelliteList() {
        viewModelScope.launch(Dispatchers.IO) {
            satelliteGetListUseCase.invoke(Unit).collectLatest {
                satelliteListFlow.value = it
            }
        }
    }

    fun getSatelliteById(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            satelliteGetByIdUseCase.invoke(SatelliteGetByIdUseCase.Params(id)).collectLatest {
                satelliteFlow.value = it
            }
        }
    }

    fun getSatelliteDetailById(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            satelliteDetailGetByIdUseCase.invoke(SatelliteDetailGetByIdUseCase.Params(id)).collectLatest {
                satelliteDetailFlow.value = it
            }
        }
    }

    fun getSatellitePositionsById(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            satellitePositionsByIdUseCase.invoke(SatellitePositionsByIdUseCase.Params(id)).collectLatest {
                satellitePositionsFlow.value = it
            }
        }
    }
}