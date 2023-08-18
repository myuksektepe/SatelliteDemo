package satellite.demo.core

import satellite.demo.core.base.BaseError


/**
 * Created by Murat Yüksektepe on 18.08.2023.
 * muratyuksektepe.com
 * yuksektepemurat@gmail.com
 */
sealed class Resource<out T> {
    data class Success<out T>(val data: T) : Resource<T>()
    data class Error(val exception: BaseError) : Resource<Nothing>()
    object Loading : Resource<Nothing>()
}