package satellite.demo.presentation.usecase

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.last
import satellite.demo.core.Resource
import satellite.demo.core.base.BaseUseCase
import satellite.demo.data.DataRepository
import satellite.demo.di.IODispatcher
import satellite.demo.domain.models.Satellite
import javax.inject.Inject


/**
 * Created by Murat Yüksektepe on 18.08.2023.
 * muratyuksektepe.com
 * yuksektepemurat@gmail.com
 */
class SatelliteUseCase @Inject constructor(
    private val dataRepository: DataRepository,
    @IODispatcher dispatcher: CoroutineDispatcher
) : BaseUseCase<Unit, List<Satellite>>(dispatcher) {

    override suspend fun execute(params: Unit?): Resource<List<Satellite>> = dataRepository.getSatellites()

}