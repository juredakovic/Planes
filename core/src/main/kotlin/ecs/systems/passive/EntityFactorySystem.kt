package ecs.systems.passive

import assetstorage.RegionNames.Companion.BIRD
import assetstorage.RegionNames.Companion.GAME_BACKGROUND
import assetstorage.RegionNames.Companion.PLANE
import com.badlogic.ashley.core.EntitySystem
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import ecs.components.BackgroundComponent
import ecs.components.BirdComponent
import ecs.components.BoundsComponent
import ecs.components.CleanUpComponent
import ecs.components.DimensionComponent
import ecs.components.MovementComponent
import ecs.components.PlanePlayerComponent
import ecs.components.PositionComponent
import ecs.components.TextureComponent
import ecs.components.WorldWrapComponent
import ecs.components.ZOrderComponent
import ktx.ashley.entity
import ktx.ashley.with

class EntityFactorySystem(var atlas: TextureAtlas) : EntitySystem() {

    override fun setProcessing(processing: Boolean) {
        super.setProcessing(false)
    }

    fun createPlayer()  {

        engine.entity {

            with<PlanePlayerComponent> {

            }

            val position = with<PositionComponent>() {
                x = 15f
                y = Gdx.graphics.height / 2f  - atlas.findRegion(PLANE).regionWidth / 2f
            }
            with<TextureComponent> {
                texture = TextureRegionDrawable(atlas.findRegion(PLANE))
            }

            val dimension = with<DimensionComponent> {
                width = atlas.findRegion(PLANE).regionWidth * 1f
                height = atlas.findRegion(PLANE).regionHeight * 1f
            }

            with<MovementComponent>{
                movement.y -= 0.981f * 1/2
            }

            with<BoundsComponent>{
                boundsRectangle.setPosition(position.x, position.y)
                boundsRectangle.setSize(dimension.width, dimension.height)

            }

            with<WorldWrapComponent>{

            }

            with <ZOrderComponent> {
                zAxis = 1
            }
        }

    }

    fun createBird()  {
        engine.entity {
            with<BirdComponent> {  }

            val position = with<PositionComponent> {

                y = MathUtils.random(0f, Gdx.graphics.height.toFloat() - atlas.findRegion(BIRD).regionHeight.toFloat())
                x = Gdx.graphics.width.toFloat()

            }
            with<TextureComponent> {
                texture = TextureRegionDrawable(atlas.findRegion(BIRD))
            }
            val dimension = with<DimensionComponent>{

                width = atlas.findRegion(BIRD).regionWidth * 1f
                height = atlas.findRegion(BIRD).regionHeight * 1f
            }

            with<MovementComponent> {

                movement.x -= (150 * Gdx.graphics.deltaTime)

            }
            with<BoundsComponent> {
                boundsRectangle.setPosition(position.x, position.y)
                boundsRectangle.setSize(dimension.width, dimension.height)
            }
            with<CleanUpComponent> {

            }

            with<ZOrderComponent> {
                zAxis = 1
            }
        }
    }

    fun createBackground(){
       engine.entity {
           with<BackgroundComponent> {

           }

           with<TextureComponent> {
               texture = TextureRegionDrawable(atlas.findRegion(GAME_BACKGROUND))
           }

           with<PositionComponent>() {
               x = 0f
               y = 0f
           }

           with<DimensionComponent> {
               width = atlas.findRegion(GAME_BACKGROUND).regionWidth.toFloat()
               height = atlas.findRegion(GAME_BACKGROUND).regionHeight.toFloat()
           }

           with<ZOrderComponent> {
               zAxis = 1
           }
       }

    }

}
