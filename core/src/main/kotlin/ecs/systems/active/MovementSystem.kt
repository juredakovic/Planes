package ecs.systems.active

import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.systems.IteratingSystem
import ecs.components.MovementComponent
import ecs.components.PositionComponent
import ktx.ashley.allOf

class MovementSystem : IteratingSystem(allOf(PositionComponent::class, MovementComponent::class).get()) {

    override fun processEntity(entity: Entity?, deltaTime: Float) {

        val position : PositionComponent = PositionComponent.POSITIONMAPPER[entity]
        val movement : MovementComponent = MovementComponent.MOVEMENTMAPPER[entity]
        position.x +=  movement.movement.x 
        position.y += movement.movement.y

    }

}
