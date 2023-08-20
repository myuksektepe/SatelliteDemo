package satellite.demo.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Filter
import android.widget.Filterable
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import satellite.demo.databinding.SatelliteListItemBinding
import satellite.demo.domain.models.Satellite
import java.util.Locale

/**
 * Created by Murat Yüksektepe on 20.08.2023.
 * muratyuksektepe.com
 * yuksektepemurat@gmail.com
 */
class SatelliteAdapter : ListAdapter<Satellite.ViewEntity, SatelliteAdapter.ViewHolder>(diffUtil), Filterable {

    private var originalList: List<Satellite.ViewEntity>? = null

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


    fun setList(list: List<Satellite.ViewEntity>?) {
        originalList = list
        submitList(list)
    }

    override fun getFilter(): Filter = customFilter

    // Filter
    private val customFilter = object : Filter() {
        override fun performFiltering(constraint: CharSequence?): FilterResults {
            val filteredList = mutableListOf<Satellite.ViewEntity>()
            if (constraint.isNullOrEmpty()) {
                originalList?.let { filteredList.addAll(it) }
            } else {
                originalList?.forEach { item ->
                    if (item.name.lowercase(Locale.ENGLISH).startsWith(constraint.toString().lowercase(Locale.ENGLISH))) {
                        filteredList.add(item)
                    }
                }
            }
            val results = FilterResults()
            results.values = filteredList
            return results
        }

        override fun publishResults(constraint: CharSequence?, results: FilterResults?) {
            results?.let {
                submitList(it.values as List<Satellite.ViewEntity>)
            }
        }

    }
}