package satellite.demo.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityRetainedComponent
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import satellite.demo.data.DataRepository
import satellite.demo.data.DataRepositoryImpl
import javax.inject.Singleton


/**
 * Created by Murat Yüksektepe on 18.08.2023.
 * muratyuksektepe.com
 * yuksektepemurat@gmail.com
 */


@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun provideRepository(dataRepositoryImpl: DataRepositoryImpl): DataRepository

}
