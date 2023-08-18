package satellite.demo.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import satellite.demo.data.DataRepository
import satellite.demo.presentation.usecase.SatelliteUseCase
import javax.inject.Singleton


/**
 * Created by Murat Yüksektepe on 18.08.2023.
 * muratyuksektepe.com
 * yuksektepemurat@gmail.com
 */


@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    @Singleton
    fun provideSatelliteUseCase(
        repository: DataRepository,
        @IODispatcher dispatcher: CoroutineDispatcher
    ) = SatelliteUseCase(repository, dispatcher)
}
