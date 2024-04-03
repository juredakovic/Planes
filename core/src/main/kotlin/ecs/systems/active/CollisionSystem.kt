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
import common.GameManager
import ecs.components.ParcelComponent
import ecs.components.PlaneComponent
import ecs.components.PlanePlayerComponent
import ecs.components.SkyscraperComponent

class CollisionSystem : IteratingSystem(allOf(BoundsComponent::class, PositionComponent::class, DimensionComponent::class).get()) {

    companion object {
        var PLANE_FAMILY: Family = allOf(PlanePlayerComponent::class, BoundsComponent::class).get()
        var BIRDS_FAMILY: Family = allOf(BirdComponent::class, BoundsComponent::class).get()
        var PARCELS_FAMILY: Family = allOf(ParcelComponent::class, BoundsComponent::class).get()
        var OTHER_PLANES_FAMILY: Family = allOf(PlaneComponent::class, BoundsComponent::class).get()
        var SKYSCRAPER_FAMILY: Family =
            allOf(SkyscraperComponent::class, BoundsComponent::class).get()
    }

    override fun processEntity(entity: Entity?, deltaTime: Float) {
        if (GameManager.isGameOver()) {
            return
        }
        val planeArr: ImmutableArray<Entity> = engine.getEntitiesFor(PLANE_FAMILY)
        val birdsArr: ImmutableArray<Entity> = engine.getEntitiesFor(BIRDS_FAMILY)
        val parcelArr: ImmutableArray<Entity> = engine.getEntitiesFor(PARCELS_FAMILY)
        val otherPlanesArr: ImmutableArray<Entity> = engine.getEntitiesFor(OTHER_PLANES_FAMILY)
        val skyScraperArr: ImmutableArray<Entity> = engine.getEntitiesFor(SKYSCRAPER_FAMILY)
        for (planeEntity: Entity in planeArr) {
            val planeBounds: BoundsComponent = BoundsComponent.BOUNDSMAPPER.get(planeEntity)

            for (birdEntity: Entity in birdsArr) {
                val bird: BirdComponent = BirdComponent.BIRDMAPPER.get(birdEntity)

                when {
                    (bird.isBirdHit) -> continue
                }

                val birdBounds: BoundsComponent = BoundsComponent.BOUNDSMAPPER.get(birdEntity)

                when {
                    (Intersector.overlaps(
                        planeBounds.boundsRectangle,
                        birdBounds.boundsRectangle
                    )) -> {
                        bird.isBirdHit = true
                        GameManager.damage()
                        engine.removeEntity(birdEntity)
                    }
                }
            }
            for (parcelEntity: Entity in parcelArr) {
                val parcel: ParcelComponent = ParcelComponent.PARCELMAPPER.get(parcelEntity)
                when {
                    (parcel.isParcelCollected) -> continue
                }

                val parcelBounds: BoundsComponent = BoundsComponent.BOUNDSMAPPER.get(parcelEntity)

                when {
                    (Intersector.overlaps(
                        planeBounds.boundsRectangle,
                        parcelBounds.boundsRectangle
                    )) -> {
                        parcel.isParcelCollected = true
                        GameManager.pickPackage()
                        engine.removeEntity(parcelEntity)
                    }
                }
            }

            for (otherPlaneEntity: Entity in otherPlanesArr) {
                val otherPlane: PlaneComponent = PlaneComponent.PLANEMAPPER.get(otherPlaneEntity)
                when {
                    (otherPlane.isCollided) -> continue
                }

                val otherPlanesBounds: BoundsComponent =
                    BoundsComponent.BOUNDSMAPPER.get(otherPlaneEntity)

                when {
                    (Intersector.overlaps(
                        planeBounds.boundsRectangle,
                        otherPlanesBounds.boundsRectangle
                    )) -> {
                        otherPlane.isCollided = true
                        GameManager.damageBig()
                        engine.removeEntity(otherPlaneEntity)
                        engine.removeEntity(planeEntity)
                    }
                }
            }

            for (skyScraperEntity: Entity in skyScraperArr) {
                val skyScraper: SkyscraperComponent =
                    SkyscraperComponent.SKYSCRAPERMAPPER.get(skyScraperEntity)

                when {
                    (skyScraper.isSkyscraperHit) -> continue
                }

                val skyScraperBounds: BoundsComponent =
                    BoundsComponent.BOUNDSMAPPER.get(skyScraperEntity)

                when {
                    (Intersector.overlaps(
                        planeBounds.boundsRectangle,
                        skyScraperBounds.boundsRectangle
                    )) -> {
                        skyScraper.isSkyscraperHit = true
                        GameManager.damageBig()
                        engine.removeEntity(planeEntity)
                    }
                }
            }
        }
    }
}
