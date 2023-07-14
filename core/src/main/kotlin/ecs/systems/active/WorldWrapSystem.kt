package ecs.systems.active

import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.systems.IteratingSystem
import com.badlogic.gdx.Gdx
import ecs.components.DimensionComponent
import ecs.components.PositionComponent
import ecs.components.WorldWrapComponent
import ktx.ashley.allOf

class WorldWrapSystem : IteratingSystem(allOf(WorldWrapComponent::class, PositionComponent::class, DimensionComponent::class).get()) {
    override fun processEntity(entity: Entity?, deltaTime: Float) {


        val position: PositionComponent = PositionComponent.POSITIONMAPPER.get(entity)
        val dimension: DimensionComponent = DimensionComponent.DIMENSIONMAPPER.get(entity)

        when {
            (position.x + dimension.width > Gdx.graphics.width) ->
                position.x = Gdx.graphics.width - dimension.width
        }
        when {
            (position.x < 0) -> position.x = 0f
        }
        when {
            (position.y + dimension.height > Gdx.graphics.height) -> position.y =
                Gdx.graphics.height - dimension.height
        }

        when {
            (position.y < 0) -> position.y = 0f
        }

    }

}
