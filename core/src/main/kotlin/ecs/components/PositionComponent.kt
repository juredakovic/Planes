package ecs.components
import com.badlogic.ashley.core.Component
import ktx.ashley.*

class PositionComponent() : Component {

    companion object {

        val POSITIONMAPPER = mapperFor<PositionComponent>()
    }

    var x : Float = 0f
    var y : Float= 0f
}
