package ecs.systems.passive

import assetstorage.RegionNames.Companion.BIRD
import assetstorage.RegionNames.Companion.GAME_BACKGROUND
import assetstorage.RegionNames.Companion.PLANE
import assetstorage.RegionNames.Companion.DELIVERY_BOX
import com.badlogic.ashley.core.EntitySystem
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import common.GameManager
import config.GameConfig
import ecs.components.BackgroundComponent
import ecs.components.BirdComponent
import ecs.components.BoundsComponent
import ecs.components.DimensionComponent
import ecs.components.MovementComponent
import ecs.components.ParcelComponent
import ecs.components.PlaneComponent
import ecs.components.PlanePlayerComponent
import ecs.components.PositionComponent
import ecs.components.TextureComponent
import ecs.components.WorldWrapComponent
import ecs.components.ZOrderComponent
import ktx.ashley.entity
import ktx.ashley.with
import kotlin.random.Random

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
                width = GameConfig.PLANE_WIDTH * 1f
                height = GameConfig.PLANE_HEIGHT * 1f
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

                y = MathUtils.random(0f, GameConfig.WORLD_HEIGHT - GameConfig.BIRD_HEIGHT)
                x = GameConfig.WORLD_WIDTH

            }
            with<TextureComponent> {
                texture = TextureRegionDrawable(atlas.findRegion(BIRD))
            }

            val dimension = with<DimensionComponent>{

                width = GameConfig.BIRD_WIDTH * 1f
                height = GameConfig.BIRD_HEIGHT * 1f
            }
            with<MovementComponent> {

                movement.x -= GameManager.getBirdSpeed() * Gdx.graphics.deltaTime

            }
            with<BoundsComponent> {
                boundsRectangle.setPosition(position.x, position.y)
                boundsRectangle.setSize(dimension.width, dimension.height)
            }
            with<ZOrderComponent> {
                zAxis = 1
            }
        }
    }

    fun createParcel() {
        engine.entity {
            with<ParcelComponent> {}

            val position = with<PositionComponent> {
                 x = MathUtils.random(
                    0f,
                    Gdx.graphics.width.toFloat() - GameConfig.DELIVERY_BOX_HEIGHT
                )
                 y = GameConfig.WORLD_HEIGHT
            }
            with<TextureComponent> {
                texture = TextureRegionDrawable(atlas.findRegion(DELIVERY_BOX))
            }

            val dimension = with<DimensionComponent>{
                width = GameConfig.DELIVERY_BOX_WIDTH * 1f
                height = GameConfig.DELIVERY_BOX_HEIGHT * 1f
            }
            with<MovementComponent>{
                movement.y -= (GameManager.getPackageSpeed() * Gdx.graphics.deltaTime)
            }
            with<BoundsComponent>{
                boundsRectangle.setPosition(position.x, position.y)
                boundsRectangle.setSize(dimension.width, dimension.height)
            }
            with<ZOrderComponent>{
                zAxis = 1
            }
        }
    }
    fun createPlane() {

        val randomOpponent : Int = Random.nextInt(1, 6)

        val selectedOpponentRegionName : String = "plane" + randomOpponent
        engine.entity() {
            with<PlaneComponent>{

            }

            val position = with<PositionComponent>{
                x = GameConfig.WORLD_WIDTH
                y = MathUtils.random(0f, GameConfig.WORLD_HEIGHT - GameConfig.PLANE_OPPONENT_HEIGHT)
            }
            with<TextureComponent> {
                texture = TextureRegionDrawable(atlas.findRegion(selectedOpponentRegionName))
            }

            val dimension = with<DimensionComponent>{
                width = GameConfig.PLANE_OPPONENT_WIDTH * 1f
                height = GameConfig.PLANE_OPPONENT_HEIGHT * 1f
            }
            with<MovementComponent> {
                movement.x -= (GameManager.getOpponentPlaneSpeed() * Gdx.graphics.deltaTime)
            }
            with<BoundsComponent>{
                boundsRectangle.setPosition(position.x, position.y)
                boundsRectangle.setSize(dimension.width, dimension.height)
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
               width = GameConfig.BACKGROUND_WIDTH
               height = GameConfig.BACKGROUND_HEIGHT
           }
           with<ZOrderComponent> {
               zAxis = 1
           }
       }
    }

}
