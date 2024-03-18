package ecs.components

import com.badlogic.ashley.core.Component
import com.badlogic.gdx.utils.Pool.Poolable
import ktx.ashley.mapperFor

class PlaneComponent : Component, Poolable {

    var isCollided : Boolean = false

    companion object {
        val PLANEMAPPER = mapperFor<PlaneComponent>()
    }

    override fun reset() {
        isCollided = false
    }
}
