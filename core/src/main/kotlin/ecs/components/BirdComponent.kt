package ecs.components

import com.badlogic.ashley.core.Component
import ktx.ashley.mapperFor
class BirdComponent : Component {

    var isBirdHit : Boolean = false
    var isFlying : Boolean = false
    companion object {
        val BIRDMAPPER = mapperFor<BirdComponent>()
    }

}
