package ecs.components

import com.badlogic.ashley.core.Component
import com.badlogic.gdx.math.Vector2
import ktx.ashley.mapperFor

class MovementComponent : Component {

    companion object {
        val MOVEMENTMAPPER = mapperFor<MovementComponent>()
    }

    var movement : Vector2 = Vector2(0f,0f)

}
