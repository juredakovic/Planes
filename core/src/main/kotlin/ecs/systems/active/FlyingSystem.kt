package ecs.systems.active

import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.systems.IteratingSystem
import com.badlogic.gdx.Gdx
import ecs.components.BirdComponent
import ecs.components.MovementComponent
import ktx.ashley.allOf
import kotlin.random.Random

class FlyingSystem : IteratingSystem(allOf(BirdComponent::class, MovementComponent::class).get()) {

    override fun processEntity(entity: Entity?, deltaTime: Float) {
        val movement : MovementComponent = MovementComponent.MOVEMENTMAPPER[entity]
        val bird : BirdComponent = BirdComponent.BIRDMAPPER[entity]

        when {(bird.isFlying) ->
            movement.movement.y += Random.nextInt(25, 50)   * Gdx.graphics.deltaTime
        }

        when {(!bird.isFlying) ->
            movement.movement.y -= Random.nextInt(25, 50)   *  Gdx.graphics.deltaTime
        }

        bird.isFlying = !bird.isFlying

    }
}
