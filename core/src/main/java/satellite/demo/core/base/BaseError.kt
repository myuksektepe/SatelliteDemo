package satellite.demo.core.base


/**
 * Created by Murat Yüksektepe on 18.08.2023.
 * muratyuksektepe.com
 * yuksektepemurat@gmail.com
 */

open class BaseError(
    private val errorMessage: String?,
    var throwable: Throwable? = null
) : Throwable(errorMessage)