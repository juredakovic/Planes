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
import common.GameManager
import ktx.actors.onClick
import ktx.actors.onEnter
import ktx.actors.onExit
import ktx.assets.async.AssetStorage
import ktx.freetype.generateFont
import ktx.scene2d.Scene2DSkin
import ktx.scene2d.actors
import ktx.scene2d.button
import ktx.scene2d.label
import ktx.scene2d.table
import ktx.style.*
import ktx.scene2d.verticalGroup
import planes.Planes

class ScoreScreen(game: Planes, atlas : TextureAtlas) : AbstractPlanesScreen(game, atlas) {

    private lateinit var stage : Stage
    private val mySkin : Skin = Skin(Gdx.files.internal("skin/glassy-ui.json"))
    private var viewport : Viewport = ExtendViewport(Gdx.graphics.width.toFloat(), Gdx.graphics.height.toFloat())
    private val  assetStorage : AssetStorage = game.getAssetStorage()
    private var generator : FreeTypeFontGenerator = assetStorage.get<FreeTypeFontGenerator>(AssetPaths.FONT)

    private val fontA : BitmapFont = generator.generateFont {
        size = 20
        color = Color.WHITE
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

                defaults().pad(5f)
                background = TextureRegionDrawable(atlas.findRegion(RegionNames.BACKGROUND))
                setFillParent(true)
                align(Align.top)
                background("list")
                verticalGroup() {
                    space(25f)
                    label("Highscore")
                    pad(50f)
                    label(GameManager.getHighscore().toString())
                    label("Name")
                    pad(50f)
                    label(GameManager.getName())
                    button(style="small") {
                        onEnter { color=Color.YELLOW }
                        onExit{ color=Color.WHITE}
                        onClick {
                            game.setScreen<MenuScreen>()
                        }
                        label("Main menu"){

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

    override fun hide() {
        super.hide()
        stage.clear()
    }

    override fun resize(width: Int, height: Int) {
        //super.resize(width, height)
        viewport.update(width, height, true)
    }

    override fun dispose() {
        super.dispose()
        mySkin.dispose()
    }
}
