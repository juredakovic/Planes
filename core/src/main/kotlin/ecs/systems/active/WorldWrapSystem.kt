package ecs.systems.active

import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.systems.IteratingSystem
import config.GameConfig
import ecs.components.DimensionComponent
import ecs.components.PositionComponent
import ecs.components.WorldWrapComponent
import ktx.ashley.allOf

class WorldWrapSystem : IteratingSystem(allOf(WorldWrapComponent::class, PositionComponent::class, DimensionComponent::class).get()) {
    override fun processEntity(entity: Entity?, deltaTime: Float) {

        val position: PositionComponent = PositionComponent.POSITIONMAPPER.get(entity)
        val dimension: DimensionComponent = DimensionComponent.DIMENSIONMAPPER.get(entity)

        when {
            (position.x + dimension.width > GameConfig.WORLD_WIDTH) ->
                position.x = GameConfig.WORLD_WIDTH - dimension.width
        }

        when {
            (position.x < 0) -> position.x = 0f
        }

        when {
            (position.y + dimension.height > GameConfig.WORLD_HEIGHT) -> position.y =
                GameConfig.WORLD_HEIGHT - dimension.height
        }

        when {
            (position.y < 0) -> position.y = 0f
        }
    }

}
