package com.ktx.planes.android

import android.os.Bundle
import com.badlogic.gdx.backends.android.AndroidApplication
import com.badlogic.gdx.backends.android.AndroidApplicationConfiguration
import planes.Planes

/** Launches the Android application.  */
class AndroidLauncher : AndroidApplication() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val configuration = AndroidApplicationConfiguration()
        configuration.useImmersiveMode = true // Recommended, but not required.
        initialize(Planes(), configuration)
    }
}
