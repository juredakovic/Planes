package ecs.systems.passive

import com.badlogic.ashley.core.EntitySystem
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.GlyphLayout
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.utils.viewport.Viewport
import common.GameManager
import ktx.graphics.use

class HudRenderSystem(batch : SpriteBatch, hudViewPort: Viewport, font : BitmapFont) : EntitySystem() {

    private val padding : Float = 20.0f
    private var batch : SpriteBatch
    private var hudViewPort : Viewport
    private var font : BitmapFont
    private var layout : GlyphLayout = GlyphLayout()


    init {
        this.batch = batch
        this.hudViewPort = hudViewPort
        this.font = font
    }

    override fun update(deltaTime: Float) {
        hudViewPort.apply()
        val y : Float = hudViewPort.worldHeight - padding
        batch.use(hudViewPort.camera.combined){

            val highScore : String = "Packages: " + GameManager.getPackages()
            layout.setText(font, highScore)
            font.draw(batch, layout, padding, y)

        }

        batch.use(hudViewPort.camera.combined) {
            val health: String = "Engines :" + GameManager.getLives()
            layout.setText(font, health)
            val healthX : Float = hudViewPort.worldWidth - layout.width - padding
            font.draw(batch, layout, healthX, y)
        }


        when{(GameManager.isGameOver()) -> {

            layout.setText(font, "GAME OVER")
            val endX : Float = (hudViewPort.worldWidth + layout.width) / 2 - layout.width
            val endY : Float = (hudViewPort.worldHeight + layout.height) / 2 -layout.height
            batch.use(hudViewPort.camera.combined) {
                font.draw(batch, layout, endX, endY)
            }


        }}
        super.update(deltaTime)
    }





}
