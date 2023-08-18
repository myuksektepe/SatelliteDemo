package satellite.demo.data

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import satellite.demo.core.Resource
import satellite.demo.core.base.BaseError
import satellite.demo.core.extension.getJsonDataFromAsset
import satellite.demo.domain.models.Satellite
import satellite.demo.domain.models.SatelliteDetail
import satellite.demo.domain.models.SatellitePosition
import javax.inject.Inject


/**
 * Created by Murat Yüksektepe on 18.08.2023.
 * muratyuksektepe.com
 * yuksektepemurat@gmail.com
 */
class LocalDataSourceImpl @Inject constructor() : LocalDataSource {

    companion object {
        private const val satelliteListFile = "satellite-list.json"
        private const val satelliteDetailsFile = "satellite-details.json"
        private const val satellitePositionsFile = "satellite-positions.json"
    }

    override suspend fun getSatellites(context: Context): Resource<List<Satellite>> {
        val result = context.getJsonModel<List<Satellite>>(satelliteListFile)
        return result?.let {
            Resource.Success(it)
        } ?: run {
            Resource.Error(BaseError("Satellite list is not found!"))
        }
    }

    override suspend fun getSatelliteById(context: Context, id: Int): Resource<Satellite> {
        val result = context.getJsonModel<Satellite>(satelliteListFile)
        return result?.let {
            Resource.Success(it)
        } ?: run {
            Resource.Error(BaseError("Satellite is not found!"))
        }
    }

    override suspend fun getSatelliteDetailsById(context: Context, id: Int): Resource<SatelliteDetail> {
        val result = context.getJsonModel<SatelliteDetail>(satelliteDetailsFile)
        return result?.let {
            Resource.Success(it)
        } ?: run {
            Resource.Error(BaseError("Satellite detail is not found!"))
        }
    }

    override suspend fun getSatellitePositionsById(context: Context, id: Int): Resource<List<SatellitePosition>> {
        val result = context.getJsonModel<List<SatellitePosition>>(satellitePositionsFile)
        return result?.let {
            Resource.Success(it)
        } ?: run {
            Resource.Error(BaseError("Satellite detail is not found!"))
        }
    }


    private fun <T> Context.getJsonModel(
        fileName: String,
    ): T? {
        var model: T? = null
        try {
            getJsonDataFromAsset(this, fileName)?.let {
                val gson = Gson()
                val modelTypeObject = object : TypeToken<T>() {}.type
                model = gson.fromJson(it, modelTypeObject)
            }
        } catch (e: Exception) {
            Log.e("applog", "${e.message}")
        }
        return model
    }
}