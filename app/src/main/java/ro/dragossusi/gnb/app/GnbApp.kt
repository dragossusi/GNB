package ro.dragossusi.gnb.app

import dagger.android.AndroidInjector
import dagger.android.DaggerApplication

class GnbApp : DaggerApplication() {
    override fun applicationInjector(): AndroidInjector<out DaggerApplication> {
        return DaggerGnbComponent.builder()
            .application(this)
            .build()
    }
}