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