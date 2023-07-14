package ecs.systems.active
import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.systems.IteratingSystem
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import ecs.components.MovementComponent
import ecs.components.PlanePlayerComponent
import ktx.ashley.allOf

class PlaneInputSystem : IteratingSystem(allOf(PlanePlayerComponent::class, MovementComponent::class).get()){

    override fun processEntity(entity: Entity?, deltaTime: Float) {
        val movement : MovementComponent = MovementComponent.MOVEMENTMAPPER.get(entity)

        when {
            Gdx.input.isKeyPressed(Input.Keys.LEFT) -> { moveLeft(movement)}
            Gdx.input.isKeyPressed(Input.Keys.RIGHT) -> {moveRight(movement)}
            Gdx.input.isKeyPressed(Input.Keys.UP) -> {moveUp(movement)}
            Gdx.input.isKeyPressed(Input.Keys.DOWN) -> {moveDown(movement)}
            }
    }

    private fun moveLeft(movementComponent: MovementComponent)  {
         movementComponent.movement.x -= 25 * 0.981f * Gdx.graphics.deltaTime
    }

    private fun moveRight(movementComponent: MovementComponent) {
        movementComponent.movement.x += 25 * 0.981f * Gdx.graphics.deltaTime
    }

    private fun moveUp(movementComponent: MovementComponent) {
        movementComponent.movement.y += 25 * 0.981f * 1/2 *  Gdx.graphics.deltaTime
    }

    private fun moveDown(movementComponent: MovementComponent) {
        movementComponent.movement.y -= 25 * 0.981f * 1/2 * Gdx.graphics.deltaTime
    }

}
