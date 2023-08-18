package satellite.demo.domain.models

import com.google.gson.annotations.SerializedName


/**
 * Created by Murat Yüksektepe on 18.08.2023.
 * muratyuksektepe.com
 * yuksektepemurat@gmail.com
 */
data class SatelliteDetail(
    @SerializedName("id")
    val id: Int,

    @SerializedName("cost_per_launch")
    val costPerLaunch: Int?,

    @SerializedName("first_flight")
    val firstFlight: String?,

    @SerializedName("height")
    val height: Int?,

    @SerializedName("mass")
    val mass: Int?
)