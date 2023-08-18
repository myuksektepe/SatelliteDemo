package satellite.demo.presentation


import androidx.activity.viewModels
import androidx.lifecycle.LifecycleOwner
import dagger.hilt.android.AndroidEntryPoint
import satellite.demo.R
import satellite.demo.core.base.BaseActivity
import satellite.demo.databinding.ActivityMainBinding

@AndroidEntryPoint
class MainActivity : BaseActivity<ActivityMainBinding>(R.layout.activity_main) {

    override val viewModel: MainActivityViewModel by viewModels()
    override var lifeCycleOwner: LifecycleOwner = this

    override fun onCreate() {
        binding.txt.text = "sdfsdf"
    }

    override fun observeViewModel() {}
}