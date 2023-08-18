package satellite.demo.data

import kotlinx.coroutines.flow.Flow
import satellite.demo.core.Resource
import satellite.demo.domain.models.SatellitePosition
import satellite.demo.domain.models.Satellite
import satellite.demo.domain.models.SatelliteDetail


/**
 * Created by Murat Yüksektepe on 18.08.2023.
 * muratyuksektepe.com
 * yuksektepemurat@gmail.com
 */
interface DataRepository {
    suspend fun getSatellites(): Resource<List<Satellite>>
    suspend fun getSatelliteById(id: Int): Resource<Satellite>
    suspend fun getSatelliteDetailsById(id: Int): Resource<SatelliteDetail>
    suspend fun getSatellitePositionsById(id: Int): Resource<List<SatellitePosition>>
}