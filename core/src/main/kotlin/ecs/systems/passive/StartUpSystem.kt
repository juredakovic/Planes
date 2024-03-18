package ecs.systems.passive

import com.badlogic.ashley.core.Engine
import com.badlogic.ashley.core.EntitySystem
import ktx.ashley.getSystem

class StartUpSystem : EntitySystem() {

    lateinit var factory : EntityFactorySystem

    override fun addedToEngine(engine: Engine?) {
        super.addedToEngine(engine)
        factory = engine!!.getSystem()
        onInit()
    }

    private fun onInit() {
        factory.createBackground()
        factory.createPlane()
        factory.createPlayer()
        factory.createParcel()
        factory.createBird()
    }
}
