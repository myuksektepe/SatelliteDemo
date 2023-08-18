package satellite.demo.di

import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import satellite.demo.data.DataRepository
import satellite.demo.data.DataRepositoryImpl


/**
 * Created by Murat Yüksektepe on 18.08.2023.
 * muratyuksektepe.com
 * yuksektepemurat@gmail.com
 */

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    fun provideRepository(dataRepositoryImpl: DataRepositoryImpl): DataRepository = dataRepositoryImpl

}