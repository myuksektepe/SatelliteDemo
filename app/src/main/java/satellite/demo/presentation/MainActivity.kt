package satellite.demo.presentation

import android.util.Log
import androidx.activity.viewModels
import androidx.core.widget.addTextChangedListener
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.launch
import satellite.demo.R
import satellite.demo.core.Resource
import satellite.demo.core.base.BaseActivity
import satellite.demo.databinding.ActivityMainBinding
import satellite.demo.domain.models.Satellite
import satellite.demo.presentation.adapter.SatelliteAdapter
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity @Inject constructor(
) : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    override val viewModel: MainActivityViewModel by viewModels()
    override var lifeCycleOwner: LifecycleOwner = this
    private val satelliteAdapter: SatelliteAdapter by lazy {
        SatelliteAdapter()
    }

    override fun onCreate() {
        viewModel.getSatelliteList()
    }

    override fun observeViewModel() {

        lifecycleScope.launch {
            repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.satelliteListLiveData.observe(lifeCycleOwner) { it ->
                    when (it) {
                        is Resource.Loading -> {
                            Log.i("applog", "loading for list")
                            toggleLoading(true)
                            // binding.frmLoading.visibility = View.VISIBLE
                        }

                        is Resource.Error -> {
                            Log.e("applog", "${it.exception}")
                            toggleLoading(false)
                        }

                        is Resource.Success -> {
                            Log.i("applog", "${it.data}")
                            toggleLoading(false)
                            val latestList = mutableListOf<Satellite.ViewEntity>()
                            it.data.forEach { item ->
                                item.toViewEntity()?.let {
                                    latestList.add(it)
                                }
                            }
                            if (latestList.size > 0) setUI(latestList)
                        }
                    }
                }
            }
        }
    }


    private fun setUI(list: List<Satellite.ViewEntity>) {
        // RecyclerView
        binding.rcySatellites.apply {
            layoutManager = LinearLayoutManager(this@MainActivity, LinearLayoutManager.VERTICAL, false)
            addItemDecoration(DividerItemDecoration(this@MainActivity, LinearLayoutManager.VERTICAL))
            adapter = satelliteAdapter
        }

        // Adapter
        satelliteAdapter.setList(list)

        // Search
        binding.edtSearch.addTextChangedListener {
            satelliteAdapter.filter.filter(it.toString())
        }
    }

    private fun toggleLoading(show: Boolean) = viewModel.loadingVisibility.set(show)
}