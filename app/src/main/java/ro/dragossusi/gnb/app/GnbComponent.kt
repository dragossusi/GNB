package ro.dragossusi.gnb.app

import dagger.Component
import dagger.android.AndroidInjector
import dagger.android.support.AndroidSupportInjectionModule
import android.app.Application
import dagger.BindsInstance



@Component(
    modules = [
        AndroidSupportInjectionModule::class,
        GnbModule::class
    ]
)
interface GnbComponent : AndroidInjector<GnbApp> {

    @Component.Builder
    interface Builder {

        @BindsInstance
        fun application(application: GnbApp): Builder

        fun build(): GnbComponent
    }

}