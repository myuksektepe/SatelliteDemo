package satellite.demo.data

import satellite.demo.domain.models.SatellitePosition
import satellite.demo.domain.models.Satellite
import satellite.demo.domain.models.SatelliteDetail


/**
 * Created by Murat Yüksektepe on 18.08.2023.
 * muratyuksektepe.com
 * yuksektepemurat@gmail.com
 */
interface DataRepository {
    fun getSatellites(): List<Satellite>
    fun getSatelliteById(): Satellite
    fun getSatelliteDetailsById(): SatelliteDetail
    fun getSatellitePositionsById(): List<SatellitePosition>
}