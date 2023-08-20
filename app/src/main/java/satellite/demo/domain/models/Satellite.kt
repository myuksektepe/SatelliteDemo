package satellite.demo.domain.models

import com.google.gson.annotations.SerializedName
import satellite.demo.R


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
) {


    data class ViewEntity(
        val id: Int,
        val activityColor: Int,
        val activeStatus: String,
        val name: String
    )

    fun toViewEntity() = this.name?.let {
        ViewEntity(
            id = this.id,
            activityColor = if (this.active == true) R.color.active else R.color.passive,
            activeStatus = if (this.active == true) "Active" else "Passive",
            name = it
        )
    }
}