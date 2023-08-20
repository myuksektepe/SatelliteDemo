package satellite.demo.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import satellite.demo.R
import satellite.demo.databinding.SatelliteListItemBinding
import satellite.demo.domain.models.Satellite


/**
 * Created by Murat Yüksektepe on 20.08.2023.
 * muratyuksektepe.com
 * yuksektepemurat@gmail.com
 */
class SatelliteAdapter : ListAdapter<Satellite.ViewEntity, SatelliteAdapter.ViewHolder>(diffUtil) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = SatelliteListItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }

    inner class ViewHolder(private val binding: SatelliteListItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind(satellite: Satellite.ViewEntity) {
            binding.apply {
                txtSatelliteName.text = satellite.name
                txtSatelliteActivityStatus.text = satellite.activeStatus
                imgCircle.setColorFilter(ContextCompat.getColor(binding.root.context, satellite.activityColor), android.graphics.PorterDuff.Mode.MULTIPLY)
            }
        }
    }
}

val diffUtil = object : DiffUtil.ItemCallback<Satellite.ViewEntity>() {
    override fun areItemsTheSame(oldItem: Satellite.ViewEntity, newItem: Satellite.ViewEntity) = oldItem.id == newItem.id
    override fun areContentsTheSame(oldItem: Satellite.ViewEntity, newItem: Satellite.ViewEntity) = oldItem == newItem
}