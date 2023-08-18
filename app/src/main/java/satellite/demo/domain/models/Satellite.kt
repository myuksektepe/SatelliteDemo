package satellite.demo.domain.models

import com.google.gson.annotations.SerializedName


/**
 * Created by Murat Yüksektepe on 18.08.2023.
 * muratyuksektepe.com
 * yuksektepemurat@gmail.com
 */
data class Satellite(
    @SerializedName("id")
    val id: Int,

    @SerializedName("active")
    val active: Boolean?,

    @SerializedName("name")
    val name: String?
)