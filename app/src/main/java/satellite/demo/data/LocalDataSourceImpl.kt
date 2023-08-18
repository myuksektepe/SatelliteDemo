package satellite.demo.data

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import satellite.demo.core.Resource
import satellite.demo.core.base.BaseError
import satellite.demo.core.extension.getJsonDataFromAsset
import satellite.demo.domain.models.Satellite
import satellite.demo.domain.models.SatelliteDetail
import satellite.demo.domain.models.SatellitePosition
import java.io.BufferedReader
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
        var satelliteList: List<Satellite> = listOf()
        return try {
            getJsonDataFromAsset(context, satelliteListFile)?.let {
                val gson = Gson()
                val listSatelliteType = object : TypeToken<List<Satellite>>() {}.type
                satelliteList = gson.fromJson(it, listSatelliteType)
            }
            delay(1300) // TODO Remove it!
            Resource.Success(satelliteList)
        } catch (e: Exception) {
            Resource.Error(BaseError(e.message))
        }

    }

    override suspend fun getSatelliteById(context: Context, id: Int): Resource<Satellite> {
        var satellite: Satellite? = null
        return try {
            getJsonDataFromAsset(context, satelliteDetailsFile)?.let {
                val gson = Gson()
                val satelliteType = object : TypeToken<Satellite>() {}.type
                satellite = gson.fromJson(it, satelliteType)
            }
            if (satellite != null) Resource.Success(satellite!!)
            else Resource.Error(BaseError("Satellite is empty!"))
        } catch (e: Exception) {
            Resource.Error(BaseError(e.message))
        }
    }

    override suspend fun getSatelliteDetailsById(context: Context, id: Int): Resource<SatelliteDetail> {
        var satelliteDetail: SatelliteDetail? = null
        return try {
            getJsonDataFromAsset(context, satelliteDetailsFile)?.let {
                val gson = Gson()
                val satelliteDetailType = object : TypeToken<SatelliteDetail>() {}.type
                satelliteDetail = gson.fromJson(it, satelliteDetailType)
            }
            if (satelliteDetail != null) Resource.Success(satelliteDetail!!)
            else Resource.Error(BaseError("Satellite Detail is empty!"))
        } catch (e: Exception) {
            Resource.Error(BaseError(e.message))
        }
    }

    override suspend fun getSatellitePositionsById(context: Context, id: Int): Resource<List<SatellitePosition>> {
        var satellitePositions: List<SatellitePosition>? = null
        return try {
            getJsonDataFromAsset(context, satelliteDetailsFile)?.let {
                val gson = Gson()
                val satellitePositionType = object : TypeToken<List<SatellitePosition>>() {}.type
                satellitePositions = gson.fromJson(it, satellitePositionType)
            }
            if (satellitePositions != null) Resource.Success(satellitePositions!!)
            else Resource.Error(BaseError("Satellite Positions are empty!"))
        } catch (e: Exception) {
            Resource.Error(BaseError(e.message))
        }
    }
}