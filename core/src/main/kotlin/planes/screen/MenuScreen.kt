package planes.screen

import assetstorage.AssetPaths
import assetstorage.RegionNames.Companion.BACKGROUND
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.BitmapFont
import ktx.scene2d.*
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import com.badlogic.gdx.utils.Align
import com.badlogic.gdx.utils.viewport.ExtendViewport
import com.badlogic.gdx.utils.viewport.Viewport
import common.GameManager
import ktx.actors.onClick
import ktx.actors.onEnter
import ktx.actors.onExit
import ktx.assets.async.AssetStorage
import ktx.freetype.generateFont
import ktx.style.label
import planes.Planes

class MenuScreen(game : Planes, atlas: TextureAtlas) : AbstractPlanesScreen(game, atlas) {

private lateinit var stage : Stage
private val mySkin : Skin = Skin(Gdx.files.internal("skin/glassy-ui.json"))
private var viewport : Viewport = ExtendViewport(Gdx.graphics.width.toFloat(), Gdx.graphics.height.toFloat())
    private val  assetStorage : AssetStorage = game.getAssetStorage()
    private var generator : FreeTypeFontGenerator = assetStorage.get<FreeTypeFontGenerator>(
        AssetPaths.FONT)


    private val fontA : BitmapFont = generator.generateFont {
        size = 20
        color = Color.WHITE
    }


    override fun show() {

    stage = Stage(viewport)
    Scene2DSkin.defaultSkin = mySkin

    mySkin.label {
        font = fontA

    }

    stage.actors {

        table {
            background = TextureRegionDrawable(atlas.findRegion(BACKGROUND))
            setFillParent(true)
            align(Align.center)

                verticalGroup {
                    space(25f)
                    button(style = "small") {
                        label("New Game") {
                        }
                        onEnter { color=Color.YELLOW }
                        onExit{ color=Color.WHITE}
                        onClick {
                            GameManager.setDifficulty("easy")
                            game.setScreen<GameScreen>()

                        }
                    }
                    button(style = "small") {
                        label("Options") {
                        }
                        onEnter { color=Color.YELLOW }
                        onExit{ color=Color.WHITE}
                        onClick{
                            game.setScreen<OptionsScreen>()
                        }
                    }
                    button(style = "small") {
                        label("Exit")
                        onEnter { color=Color.YELLOW }
                        onExit{ color=Color.WHITE}
                        onClick {
                            Gdx.app.exit()

                        }
                    }
                }
            }
        }

    Gdx.input.inputProcessor = stage
}

    override fun resize(width: Int, height: Int) {
        viewport.update(width, height, true)
    }

    override fun hide() {
        super.hide()
        stage.clear()
    }
override fun render(delta : Float) {
    stage.run {
        viewport.apply()
        act()
        draw()
    }
}

    override fun dispose() {
        super.dispose()
        mySkin.dispose()
        stage.dispose()
    }

}
