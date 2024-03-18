package ecs.components

import com.badlogic.ashley.core.Component
import com.badlogic.gdx.utils.Pool.Poolable
import ktx.ashley.mapperFor

class ParcelComponent : Component, Poolable {

    var isParcelCollected : Boolean = false

    companion object {
        var PARCELMAPPER = mapperFor<ParcelComponent>()
    }

    override fun reset() {
        isParcelCollected = false
    }

}
