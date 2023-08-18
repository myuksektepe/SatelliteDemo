package satellite.demo.core.base

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.LifecycleOwner

/**
 * Created by Murat Yüksektepe on 18.08.2023.
 * muratyuksektepe.com
 * yuksektepemurat@gmail.com
 */

abstract class BaseActivity<VDB : ViewDataBinding>(private val resLayout: Int) : AppCompatActivity() {

    private var mBinding: VDB? = null
    val binding: VDB get() = mBinding!!

    abstract val viewModel: BaseViewModel

    abstract var lifeCycleOwner: LifecycleOwner

    abstract fun onCreate()
    abstract fun observeViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // Binding
        initBinding().apply {
            setContentView(mBinding!!.root)
        }

        // Observers
        observeViewModel()

        //
        onCreate()
    }

    private fun initBinding() {
        mBinding?.lifecycleOwner = lifeCycleOwner
        mBinding = DataBindingUtil.inflate(layoutInflater, resLayout, null, false)
    }

    override fun onDestroy() {
        mBinding = null
        super.onDestroy()
    }
}