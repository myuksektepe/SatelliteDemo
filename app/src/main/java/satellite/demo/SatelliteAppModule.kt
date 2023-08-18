package satellite.demo

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton


/**
 * Created by Murat Yüksektepe on 18.08.2023.
 * muratyuksektepe.com
 * yuksektepemurat@gmail.com
 */
@Module
@InstallIn(SingletonComponent::class)
object SatelliteAppModule {

    @Singleton
    @Provides
    fun provideApp(@ApplicationContext app: Context): SatelliteApp = app as SatelliteApp
}