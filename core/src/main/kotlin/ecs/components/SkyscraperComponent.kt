package ecs.components

import com.badlogic.ashley.core.Component
import com.badlogic.gdx.utils.Pool
import ktx.ashley.mapperFor

class SkyscraperComponent : Component, Pool.Poolable {

    var isSkyscraperHit : Boolean = false
    companion object {
        val SKYSCRAPERMAPPER = mapperFor<SkyscraperComponent>()
    }
    override fun reset() {
        isSkyscraperHit = false
    }


}
