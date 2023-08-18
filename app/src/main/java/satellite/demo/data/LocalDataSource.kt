package satellite.demo.data

import android.content.Context
import java.io.BufferedReader


/**
 * Created by Murat Yüksektepe on 18.08.2023.
 * muratyuksektepe.com
 * yuksektepemurat@gmail.com
 */
class LocalDataSource {

    companion object {
        private const val satelliteListFile = "file://android_asset/satellite-list.json"
        private const val satelliteDetailsFile = "file://android_asset/satellite-details.json"
        private const val satellitePositionsFile = "file://android_asset/satellite-positions.json"
    }
    fun readAsset(context: Context, fileName: String): String =
        context
            .assets
            .open(fileName)
            .bufferedReader()
            .use(BufferedReader::readText)
}