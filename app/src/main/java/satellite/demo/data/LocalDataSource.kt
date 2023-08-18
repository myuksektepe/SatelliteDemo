package satellite.demo.data

import android.content.Context
import kotlinx.coroutines.flow.Flow
import satellite.demo.core.Resource
import satellite.demo.domain.models.Satellite
import satellite.demo.domain.models.SatelliteDetail
import satellite.demo.domain.models.SatellitePosition


/**
 * Created by Murat Yüksektepe on 18.08.2023.
 * muratyuksektepe.com
 * yuksektepemurat@gmail.com
 */
interface LocalDataSource {
    suspend fun getSatellites(context: Context): Resource<List<Satellite>>
    suspend fun getSatelliteById(context: Context, id: Int): Resource<Satellite>
    suspend fun getSatelliteDetailsById(context: Context, id: Int): Resource<SatelliteDetail>
    suspend fun getSatellitePositionsById(context: Context, id: Int): Resource<List<SatellitePosition>>
}