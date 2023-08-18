package satellite.demo.presentation

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import satellite.demo.core.base.BaseViewModel
import satellite.demo.data.DataRepository
import satellite.demo.data.DataRepositoryImpl
import satellite.demo.data.LocalDataSource
import javax.inject.Inject


/**
 * Created by Murat Yüksektepe on 18.08.2023.
 * muratyuksektepe.com
 * yuksektepemurat@gmail.com
 */

@HiltViewModel
class MainActivityViewModel @Inject constructor(
    @ApplicationContext val context: Context,
    private var dataRepository: DataRepository,
) : BaseViewModel() {

    @Inject
    lateinit var dataSource: LocalDataSource


    fun getSatelliteList() {
        dataRepository = DataRepositoryImpl(context, dataSource)
        val list = dataRepository.getSatelliteById()
        Log.i("applog", "$list")
    }

}