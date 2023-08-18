package satellite.demo.domain.models


/**
 * Created by Murat Yüksektepe on 18.08.2023.
 * muratyuksektepe.com
 * yuksektepemurat@gmail.com
 */
data class SatelliteDetail(
    val id: Int,
    val costPerLaunch: Long?,
    val firstFlight: String?,
    val height: Int?,
    val mass: Long?
)