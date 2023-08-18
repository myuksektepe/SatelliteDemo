package satellite.demo.presentation


import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import satellite.demo.R
import satellite.demo.core.Resource
import satellite.demo.core.base.BaseActivity
import satellite.demo.databinding.ActivityMainBinding
import satellite.demo.di.IODispatcher
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity @Inject constructor(
) : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    override val viewModel: MainActivityViewModel by viewModels()
    override var lifeCycleOwner: LifecycleOwner = this

    override fun onCreate() {
        // viewModel.getSatelliteList()
        viewModel.getSatelliteDetailById(3)
    }

    override fun observeViewModel() {

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.satelliteListLiveData.observe(lifeCycleOwner) {
                    when (it) {
                        is Resource.Loading -> {
                            Log.i("applog", "loading for list")
                        }

                        is Resource.Error -> {
                            Log.e("applog", "${it.exception}")
                        }

                        is Resource.Success -> {
                            Log.i("applog", "${it.data}")
                        }
                    }
                }



                viewModel.satelliteDetailLiveData.observe(lifeCycleOwner) {
                    when (it) {
                        is Resource.Loading -> {
                            Log.i("applog", "loading for detail")
                        }

                        is Resource.Error -> {
                            Log.e("applog", "${it.exception}")
                        }

                        is Resource.Success -> {
                            Log.i("applog", "${it.data}")
                        }

                        else -> {}
                    }
                }
            }
        }


        /*
        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {

                viewModel.satelliteList.collectLatest {
                    when (it) {
                        is Resource.Loading -> {
                            Log.i("applog", "loading for list")
                        }

                        is Resource.Error -> {
                            Log.e("applog", "${it.exception}")
                        }

                        is Resource.Success -> {
                            Log.i("applog", "${it.data}")
                        }
                    }
                }

                /*
                                viewModel.satelliteDetail.collectLatest {
                    when (it) {
                        is Resource.Loading -> {
                            Log.i("applog", "loading for detail")
                        }

                        is Resource.Error -> {
                            Log.e("applog", "${it.exception}")
                        }

                        is Resource.Success -> {
                            Log.i("applog", "${it.data}")
                        }

                        else -> {}
                    }
                }

                 */

            }
        }
         */
    }
}