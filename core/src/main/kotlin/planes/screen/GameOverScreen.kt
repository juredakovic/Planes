package planes.screen

import assetstorage.AssetPaths
import assetstorage.RegionNames
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import com.badlogic.gdx.utils.Align
import com.badlogic.gdx.utils.viewport.ExtendViewport
import com.badlogic.gdx.utils.viewport.Viewport
import ktx.actors.onClick
import ktx.assets.async.AssetStorage
import ktx.freetype.generateFont
import ktx.scene2d.Scene2DSkin
import ktx.scene2d.actors
import ktx.scene2d.button
import ktx.scene2d.horizontalGroup
import ktx.scene2d.label
import ktx.scene2d.table
import ktx.scene2d.verticalGroup
import ktx.style.label
import planes.Planes

class GameOverScreen(game : Planes, atlas : TextureAtlas) : AbstractPlanesScreen(game, atlas) {

    private lateinit var stage: Stage
    private val mySkin: Skin = Skin(Gdx.files.internal("skin/glassy-ui.json"))
    private var viewport: Viewport =
        ExtendViewport(Gdx.graphics.width.toFloat(), Gdx.graphics.height.toFloat())
    private val  assetStorage : AssetStorage = game.getAssetStorage()
    private var generator : FreeTypeFontGenerator = assetStorage.get<FreeTypeFontGenerator>(
        AssetPaths.FONT)

    private val fontA : BitmapFont = generator.generateFont {
        size = 20
        color = Color.FIREBRICK
    }


    override fun show() {
        stage = Stage(viewport)
        Scene2DSkin.defaultSkin = mySkin

        mySkin.label {
            font = fontA
            fontColor = Color.BLUE
        }

        stage.actors {
            table {
                background = TextureRegionDrawable(atlas.findRegion(RegionNames.BACKGROUND))
                setFillParent(true)
                background("list")
                align(Align.center)

                verticalGroup {
                    space(25f)
                    label("Game Over") {

                    }
                    horizontalGroup {
                        button(style = "small") {
                            label("Main Menu") {
                            }
                            onClick {
                                game.setScreen<MenuScreen>()
                            }
                        }
                        button(style = "small") {
                            label("Highscores") {
                            }
                            onClick {
                                game.setScreen<ScoreScreen>()
                            }
                        }
                    }
                }
            }
        }
        Gdx.input.inputProcessor = stage
    }

    override fun render(delta: Float) {
        stage.act()
        stage.draw()
    }

    override fun resize(width: Int, height: Int) {
        viewport.update(width, height, true)
    }

    override fun hide() {
        super.hide()
        stage.clear()
    }

    override fun dispose() {
        super.dispose()
        mySkin.dispose()
    }
}
