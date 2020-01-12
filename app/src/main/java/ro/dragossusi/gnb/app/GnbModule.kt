package ro.dragossusi.gnb.app

import android.content.Context
import dagger.Module
import dagger.Provides
import ro.dragossusi.api.gnb.ProductsApi
import ro.dragossusi.api.gnb.RatesApi
import ro.dragossusi.api.gnb.modules.RetrofitModule

@Module(
    includes = [
        RetrofitModule::class,
        RatesApi.Module::class,
        ProductsApi.Module::class,
        ActivityBuilder::class
    ]
)
class GnbModule {

    @Provides
    fun context(app: GnbApp): Context = app

}