package satellite.demo.data

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
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
    private val dataSource: LocalDataSource
) : DataRepository {

    override fun getSatellites(): List<Satellite> = dataSource.getSatelliteList(context)

    override fun getSatelliteById(): Satellite = Satellite(1, null, null)

    override fun getSatelliteDetailsById(): SatelliteDetail = SatelliteDetail(1, null, null, null, null)

    override fun getSatellitePositionsById(): List<SatellitePosition> = listOf(SatellitePosition(0.1, 0.3))

}