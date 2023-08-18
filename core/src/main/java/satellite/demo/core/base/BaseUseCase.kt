package satellite.demo.core.base

import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.withContext
import satellite.demo.core.Resource
import java.lang.RuntimeException


/**
 * Created by Murat Yüksektepe on 18.08.2023.
 * muratyuksektepe.com
 * yuksektepemurat@gmail.com
 */
abstract class BaseUseCase<in P, T> constructor(
    private val dispatcher: CoroutineDispatcher
) {
    suspend operator fun invoke(params: P?): Flow<Resource<T>> {
        return try {
            withContext(dispatcher) {
                flow {
                    emit(Resource.Loading)
                    emit(execute(params))
                }
            }
        } catch (e: Exception) {
            flow {
                emit(Resource.Error(BaseError(e.message)))
            }
        }
    }

    @Throws(RuntimeException::class)
    abstract suspend fun execute(params: P?): Resource<T>
}