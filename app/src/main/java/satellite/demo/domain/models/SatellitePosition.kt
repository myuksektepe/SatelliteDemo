package satellite.demo.domain.models


/**
 * Created by Murat Yüksektepe on 18.08.2023.
 * muratyuksektepe.com
 * yuksektepemurat@gmail.com
 */

data class SatellitePositionList(
    val list: List<SatellitePositionItem>
)

data class SatellitePositionItem(
    val id: String,
    val positions: List<SatellitePosition>
)

data class SatellitePosition(
    val posX: Double?,
    val posY: Double?,
)