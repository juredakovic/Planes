package ecs.systems.active

import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.systems.IteratingSystem
import ecs.components.BirdComponent
import ecs.components.BoundsComponent
import ecs.components.DimensionComponent
import ecs.components.PositionComponent
import ktx.ashley.allOf
import com.badlogic.ashley.core.Family
import com.badlogic.ashley.utils.ImmutableArray
import com.badlogic.gdx.math.Intersector
import ecs.components.PlanePlayerComponent

class CollisionSystem : IteratingSystem(allOf(BirdComponent::class, BoundsComponent::class, PositionComponent::class, DimensionComponent::class).get()) {

    companion object {
        var PLANE_FAMILY : Family = allOf(PlanePlayerComponent::class, BoundsComponent::class).get()
        var BIRDS_FAMILY : Family = allOf(BirdComponent::class, BoundsComponent::class).get()
    }
    override fun processEntity(entity: Entity?, deltaTime: Float) {

        val planeArr : ImmutableArray<Entity> = engine.getEntitiesFor(PLANE_FAMILY)
        val birdsArr : ImmutableArray<Entity> = engine.getEntitiesFor(BIRDS_FAMILY)

        for(planeEntity : Entity in planeArr) {
            val planeBounds : BoundsComponent = BoundsComponent.BOUNDSMAPPER.get(planeEntity)

            loop@ for(birdEntity : Entity in birdsArr){
                val bird : BirdComponent = BirdComponent.BIRDMAPPER.get(birdEntity)
                when {bird.isBirdHit ->  continue@loop }

                val birdBounds : BoundsComponent = BoundsComponent.BOUNDSMAPPER.get(birdEntity)
                when {
                    (Intersector.overlaps(
                        planeBounds.boundsRectangle,
                        birdBounds.boundsRectangle
                    )) -> {
                        bird.isBirdHit = true
                        engine.removeEntity(birdEntity)
                    }
                }
            }
        }
    }




}
