package satellite.demo.presentation.usecase

import kotlinx.coroutines.CoroutineDispatcher
import satellite.demo.core.Resource
import satellite.demo.core.base.BaseUseCase
import satellite.demo.data.DataRepository
import satellite.demo.di.IODispatcher
import satellite.demo.domain.models.SatelliteDetail
import javax.inject.Inject


/**
 * Created by Murat Yüksektepe on 18.08.2023.
 * muratyuksektepe.com
 * yuksektepemurat@gmail.com
 */
class SatelliteDetailGetByIdUseCase @Inject constructor(
    private val dataRepository: DataRepository,
    @IODispatcher dispatcher: CoroutineDispatcher
) : BaseUseCase<SatelliteDetailGetByIdUseCase.Params, SatelliteDetail>(dispatcher) {

    override suspend fun execute(params: Params?): Resource<SatelliteDetail>? {
        return params?.id?.let { dataRepository.getSatelliteDetailsById(it) }
    }

    data class Params(
        val id: Int
    )

}