package ecs.components

import com.badlogic.ashley.core.Component
import ktx.ashley.mapperFor

class ZOrderComponent : Component {

    companion object {
        val ZORDERMAPPER = mapperFor<ZOrderComponent>()
    }

    var zAxis : Int = 0
}
