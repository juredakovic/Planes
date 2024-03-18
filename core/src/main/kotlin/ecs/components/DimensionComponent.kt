package ecs.components

import com.badlogic.ashley.core.Component
import ktx.ashley.mapperFor
class DimensionComponent : Component {

    companion object{

        val DIMENSIONMAPPER = mapperFor<DimensionComponent>()
    }
    var width : Float = 1f
    var height : Float = 1f

}
