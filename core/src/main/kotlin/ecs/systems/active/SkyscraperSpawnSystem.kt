package ecs.systems.active

import com.badlogic.ashley.core.Engine
import com.badlogic.ashley.systems.IntervalSystem
import common.GameManager
import ecs.systems.passive.EntityFactorySystem
import ktx.ashley.getSystem

class SkyscraperSpawnSystem : IntervalSystem(GameManager.getSkyScraperFrequency()) {

    lateinit var factory : EntityFactorySystem

    override fun addedToEngine(engine: Engine) {
        super.addedToEngine(engine)
        factory = engine.getSystem()
    }


    override fun updateInterval() {
        factory.createSkyscraper()
    }
}
