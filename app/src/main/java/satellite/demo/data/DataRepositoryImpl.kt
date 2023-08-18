package satellite.demo.data

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import satellite.demo.core.Resource
import satellite.demo.domain.models.Satellite
import satellite.demo.domain.models.SatelliteDetail
import satellite.demo.domain.models.SatellitePosition
import javax.inject.Inject


/**
 * Created by Murat Yüksektepe on 18.08.2023.
 * muratyuksektepe.com
 * yuksektepemurat@gmail.com
 */
class DataRepositoryImpl @Inject constructor(
    @ApplicationContext private val context: Context,
    private val dataSource: LocalDataSourceImpl
) : DataRepository {

    override suspend fun getSatellites(): Resource<List<Satellite>> = dataSource.getSatellites(context)

    override suspend fun getSatelliteById(id: Int): Resource<Satellite> {
        return Resource.Success(Satellite(1, null, null))
    }

    override suspend fun getSatelliteDetailsById(id: Int): Resource<SatelliteDetail> {
        return Resource.Success(SatelliteDetail(1, null, null, null, null))
    }

    override suspend fun getSatellitePositionsById(id: Int): Resource<List<SatellitePosition>> {
        return Resource.Success(listOf(SatellitePosition(0.1, 0.3)))
    }

}