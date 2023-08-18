package satellite.demo.data

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
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

    override suspend fun getSatelliteById(id: Int): Resource<Satellite> = dataSource.getSatelliteById(context, id)

    override suspend fun getSatelliteDetailsById(id: Int): Resource<SatelliteDetail> = dataSource.getSatelliteDetailsById(context, id)

    override suspend fun getSatellitePositionsById(id: Int): Resource<List<SatellitePosition>> = dataSource.getSatellitePositionsById(context, id)

}