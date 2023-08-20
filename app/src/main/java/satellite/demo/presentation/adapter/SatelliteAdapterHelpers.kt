package satellite.demo.presentation.adapter

import androidx.recyclerview.widget.DiffUtil
import satellite.demo.domain.models.Satellite

/**
 * Created by Murat Yüksektepe on 20.08.2023.
 * muratyuksektepe.com
 * yuksektepemurat@gmail.com
 */

// DiffUtil
val diffUtil = object : DiffUtil.ItemCallback<Satellite.ViewEntity>() {
    override fun areItemsTheSame(oldItem: Satellite.ViewEntity, newItem: Satellite.ViewEntity) = oldItem.id == newItem.id
    override fun areContentsTheSame(oldItem: Satellite.ViewEntity, newItem: Satellite.ViewEntity) = oldItem == newItem
}
