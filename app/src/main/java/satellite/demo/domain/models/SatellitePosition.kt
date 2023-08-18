package satellite.demo.domain.models

import com.google.gson.annotations.SerializedName


/**
 * Created by Murat Yüksektepe on 18.08.2023.
 * muratyuksektepe.com
 * yuksektepemurat@gmail.com
 */

data class SatellitePositionList(
    @SerializedName("list")
    val list: List<SatellitePositionItem>
)

data class SatellitePositionItem(
    @SerializedName("id")
    val id: String,

    @SerializedName("positions")
    val positions: List<SatellitePosition>
)

data class SatellitePosition(
    @SerializedName("posX")
    val posX: Double?,

    @SerializedName("posY")
    val posY: Double?,
)