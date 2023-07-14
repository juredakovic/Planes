package ecs.systems.active

import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.systems.IteratingSystem
import ecs.components.BoundsComponent
import ecs.components.DimensionComponent
import ecs.components.PositionComponent
import ktx.ashley.allOf

class BoundsSystem : IteratingSystem(allOf(BoundsComponent::class, PositionComponent::class, DimensionComponent::class).get()) {
    override fun processEntity(entity: Entity?, deltaTime: Float) {

        val bounds : BoundsComponent = BoundsComponent.BOUNDSMAPPER.get(entity)
        val position : PositionComponent = PositionComponent.POSITIONMAPPER.get(entity)
        val dimension : DimensionComponent = DimensionComponent.DIMENSIONMAPPER.get(entity)

        bounds.boundsRectangle.x = position.x
        bounds.boundsRectangle.y = position.y
        bounds.boundsRectangle.width = dimension.width
        bounds.boundsRectangle.height = dimension.height
    }


}
