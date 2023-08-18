package satellite.demo.data

import android.content.Context
import satellite.demo.domain.models.Satellite
import java.io.BufferedReader
import javax.inject.Inject


/**
 * Created by Murat Yüksektepe on 18.08.2023.
 * muratyuksektepe.com
 * yuksektepemurat@gmail.com
 */
class LocalDataSource @Inject constructor() {

    companion object {
        private const val satelliteListFile = "file://android_asset/satellite-list.json"
        private const val satelliteDetailsFile = "file://android_asset/satellite-details.json"
        private const val satellitePositionsFile = "file://android_asset/satellite-positions.json"
    }

    fun getStringFromAssets(context: Context) = readAsset(context, satelliteListFile)

    fun getSatelliteList(context: Context): List<Satellite> = listOf()

    private fun readAsset(context: Context, fileName: String): String =
        context
            .assets
            .open(fileName)
            .bufferedReader()
            .use(BufferedReader::readText)
}