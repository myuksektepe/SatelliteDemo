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
import satellite.demo.domain.models.SatellitePositionList
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
        val satelliteList = getSatellites(context)
        var result: Resource<Satellite>? = null
        when (satelliteList) {
            is Resource.Success -> {
                satelliteList.data.find { satellite -> satellite.id == id }?.let {
                    result = Resource.Success(it)
                } ?: {
                    result = Resource.Error(BaseError("Satellite id might be wrong"))
                }
            }

            is Resource.Error -> {
                result = Resource.Error(satelliteList.exception)
            }

            else -> {}
        }
        return result!!
    }

    override suspend fun getSatelliteDetailsById(context: Context, id: Int): Resource<SatelliteDetail> {
        val satelliteDetailList = context.getJsonModel<List<SatelliteDetail>>(satelliteDetailsFile)
        var result: Resource<SatelliteDetail>? = null
        satelliteDetailList?.let {
            it.find { satelliteDetail -> satelliteDetail.id == id }?.let { satelliteDetail ->
                result = Resource.Success(satelliteDetail)
            } ?: {
                result = Resource.Error(BaseError("Satellite id might be wrong"))
            }
        } ?: {
            result = Resource.Error(BaseError("Satellite details list is empty!"))
        }
        return result!!
    }

    override suspend fun getSatellitePositionsById(context: Context, id: Int): Resource<List<SatellitePosition>> {
        val satellitePositionList = context.getJsonModel<SatellitePositionList>(satellitePositionsFile)
        var result: Resource<List<SatellitePosition>>? = null
        satellitePositionList?.let {
            it.list.find { satellitePositionItem -> satellitePositionItem.id == id.toString() }?.let { satellitePositionItem ->
                result = Resource.Success(satellitePositionItem.positions)
            } ?: {
                result = Resource.Error(BaseError("Satellite id might be wrong"))
            }
        } ?: {
            result = Resource.Error(BaseError("Satellite position list is empty!"))
        }
        return result!!
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