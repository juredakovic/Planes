package ecs.components

import com.badlogic.ashley.core.Component
import com.badlogic.gdx.utils.Pool.Poolable
import ktx.ashley.mapperFor
class BirdComponent : Component, Poolable {

    var isBirdHit : Boolean = false
    var isFlying : Boolean = false
    companion object {
        val BIRDMAPPER = mapperFor<BirdComponent>()
    }
    override fun reset() {
        isBirdHit = false
    }

}
