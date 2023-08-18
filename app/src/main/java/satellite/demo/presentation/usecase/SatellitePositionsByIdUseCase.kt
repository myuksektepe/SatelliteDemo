package satellite.demo.presentation.usecase

import kotlinx.coroutines.CoroutineDispatcher
import satellite.demo.core.Resource
import satellite.demo.core.base.BaseUseCase
import satellite.demo.data.DataRepository
import satellite.demo.di.IODispatcher
import satellite.demo.domain.models.Satellite
import satellite.demo.domain.models.SatellitePosition
import javax.inject.Inject


/**
 * Created by Murat Yüksektepe on 18.08.2023.
 * muratyuksektepe.com
 * yuksektepemurat@gmail.com
 */
class SatellitePositionsByIdUseCase @Inject constructor(
    private val dataRepository: DataRepository,
    @IODispatcher dispatcher: CoroutineDispatcher
) : BaseUseCase<SatellitePositionsByIdUseCase.Params, List<SatellitePosition>>(dispatcher) {

    override suspend fun execute(params: Params?): Resource<List<SatellitePosition>>? {
        return params?.id?.let { dataRepository.getSatellitePositionsById(it) }
    }

    data class Params(
        val id: Int
    )

}