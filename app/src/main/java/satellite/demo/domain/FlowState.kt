package satellite.demo.domain

import satellite.demo.core.base.BaseError


/**
 * Created by Murat Yüksektepe on 18.08.2023.
 * muratyuksektepe.com
 * yuksektepemurat@gmail.com
 */
sealed class FlowState<out T> {
    object Loading : FlowState<Nothing>()
    class Error(val error: BaseError?) : FlowState<BaseError>()
    class DataReceived<out T>(val data: T?) : FlowState<T>()
}