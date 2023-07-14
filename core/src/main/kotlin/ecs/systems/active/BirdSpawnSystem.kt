package ecs.systems.active

import com.badlogic.ashley.core.Engine
import com.badlogic.ashley.systems.IntervalSystem
import ecs.systems.passive.EntityFactorySystem
import ktx.ashley.getSystem

class BirdSpawnSystem : IntervalSystem(1f) {

    lateinit var factory : EntityFactorySystem

    override fun addedToEngine(engine: Engine?) {
        super.addedToEngine(engine)
        factory = engine!!.getSystem()
    }

    override fun updateInterval() {
        factory.createBird()
    }



}
