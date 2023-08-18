package satellite.demo.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
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

    private val satelliteListMLD = MutableLiveData<Resource<List<Satellite>>>()
    val satelliteListLiveData: LiveData<Resource<List<Satellite>>> get() = satelliteListMLD

    private val satelliteMLD = MutableLiveData<Resource<Satellite>>()
    val satelliteLiveData: LiveData<Resource<Satellite>> get() = satelliteMLD

    private val satelliteDetailMLD = MutableLiveData<Resource<SatelliteDetail>?>()
    val satelliteDetailLiveData: LiveData<Resource<SatelliteDetail>?> get() = satelliteDetailMLD

    private val satellitePositionsMLD = MutableLiveData<Resource<List<SatellitePosition>>>()
    val satellitePositionsLiveData: LiveData<Resource<List<SatellitePosition>>> get() = satellitePositionsMLD

    fun getSatelliteList() {
        viewModelScope.launch(Dispatchers.IO) {
            satelliteGetListUseCase.invoke(Unit).collect {
                satelliteListMLD.postValue(it)
            }
        }
    }

    fun getSatelliteById(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            satelliteGetByIdUseCase.invoke(SatelliteGetByIdUseCase.Params(id)).collect {
                satelliteMLD.postValue(it)
            }
        }
    }

    fun getSatelliteDetailById(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            satelliteDetailGetByIdUseCase.invoke(SatelliteDetailGetByIdUseCase.Params(id)).collectLatest {
                satelliteDetailMLD.postValue(it)
            }
        }
    }

    fun getSatellitePositionsById(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            satellitePositionsByIdUseCase.invoke(SatellitePositionsByIdUseCase.Params(id)).collectLatest {
                satellitePositionsMLD.postValue(it)
            }
        }
    }
}