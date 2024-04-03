package ecs.systems.active

import com.badlogic.ashley.core.Entity
import com.badlogic.ashley.systems.SortedIteratingSystem
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.Sprite
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.math.Vector2
import com.badlogic.gdx.utils.viewport.Viewport
import common.GameManager
import ecs.components.DimensionComponent
import ecs.components.PositionComponent
import ecs.components.TextureComponent
import ecs.components.ZOrderComparator
import ecs.components.ZOrderComponent
import ktx.ashley.allOf
import ktx.graphics.use

class RenderSystem(batch: SpriteBatch, viewport: Viewport, backgroundTexture : Texture) : SortedIteratingSystem(allOf(PositionComponent::class, DimensionComponent::class, TextureComponent::class, ZOrderComponent::class).get(), ZOrderComparator.comparator ) {

    private val background : Sprite = Sprite(backgroundTexture.apply {
        setWrap(Texture.TextureWrap.Repeat, Texture.TextureWrap.Repeat)
    })

    private val backgroundScrollSpeed : Vector2 = GameManager.getBackgroundScrollSpeed()
    private var batch : SpriteBatch = SpriteBatch()
    private var viewport : Viewport


    init {
        this.batch = batch
        this.viewport = viewport
    }

    override fun update(deltaTime: Float) {
        viewport.apply()
            batch.use(viewport.camera.combined) {
                background.run {
                    scroll(backgroundScrollSpeed.x * deltaTime, 0f)
                    draw(batch)
                }
            }
            super.update(deltaTime)
    }

    override fun processEntity(entity: Entity?, deltaTime: Float) {

        val position : PositionComponent = PositionComponent.POSITIONMAPPER[entity]
        val dimension : DimensionComponent = DimensionComponent.DIMENSIONMAPPER[entity]
        val texture : TextureComponent = TextureComponent.TEXTUREMAPPER[entity]

        batch.use {
                it.draw(
                    texture.texture.region,
                    position.x,
                    position.y,
                    dimension.width,
                    dimension.height
                )
            }

    }
}
