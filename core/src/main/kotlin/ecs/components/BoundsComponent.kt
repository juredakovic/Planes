package ecs.components

import com.badlogic.ashley.core.Component
import com.badlogic.gdx.math.Rectangle
import ktx.ashley.mapperFor

class BoundsComponent : Component {
    companion object {
        var BOUNDSMAPPER = mapperFor<BoundsComponent>()
    }
    var boundsRectangle : Rectangle = Rectangle(0f, 0f, 1f, 1f)

}
