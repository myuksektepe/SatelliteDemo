package satellite.demo.presentation


import android.util.Log
import androidx.activity.viewModels
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import satellite.demo.R
import satellite.demo.core.Resource
import satellite.demo.core.base.BaseActivity
import satellite.demo.databinding.ActivityMainBinding
import satellite.demo.domain.FlowState

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    override val viewModel: MainActivityViewModel by viewModels()
    override var lifeCycleOwner: LifecycleOwner = this

    override fun onCreate() {
        viewModel.getSatelliteList()
    }

    override fun observeViewModel() {
        lifecycleScope.launchWhenCreated {
            viewModel.satelliteList.collectLatest {
                when (it) {
                    is Resource.Loading -> {
                        Log.i("applog", "loading")
                    }

                    is Resource.Error -> {
                        Log.e("applog", "${it.exception}")
                    }

                    is Resource.Success -> {
                        Log.i("applog", "${it.data}")
                    }
                }
            }
        }
    }
}