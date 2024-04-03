package planes.screen

import assetstorage.AssetPaths
import assetstorage.RegionNames
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Cell.defaults
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
import ktx.scene2d.textField
import ktx.scene2d.verticalGroup
import ktx.style.label
import planes.Planes

class UserScreen(game : Planes, atlas: TextureAtlas) : AbstractPlanesScreen(game, atlas)  {

    private lateinit var stage : Stage
    private val mySkin : Skin = Skin(Gdx.files.internal("skin/glassy-ui.json"))
    private var viewport : Viewport = ExtendViewport(Gdx.graphics.width.toFloat(), Gdx.graphics.height.toFloat())
    private val  assetStorage : AssetStorage = game.getAssetStorage()
    private var generator : FreeTypeFontGenerator = assetStorage.get<FreeTypeFontGenerator>(
        AssetPaths.FONT)


    private val fontA : BitmapFont = generator.generateFont {
        size = 20
        color = Color.BLUE
    }



    override fun show() {
        stage = Stage(viewport)
        Scene2DSkin.defaultSkin = mySkin
        defaults().pad(5f)

        mySkin.label {
            font = fontA
        }

        stage.actors {
            table {
                background = TextureRegionDrawable(atlas.findRegion(RegionNames.BACKGROUND))
                setFillParent(true)
                align(Align.center)
                background("list")
                verticalGroup {
                    space(25f)
                    label("Player name : ")
                    val textField = textField(){
                    }
                    button(style = "small") {
                        label("Continue")
                        onEnter { color=Color.YELLOW }
                        onExit{ color=Color.WHITE}
                        onClick {
                            GameManager.setName(textField.text)
                            /*
                            if(game.containsScreen<UserScreen>()){
                                game.removeScreen<UserScreen>()
                                dispose()
                            }

                             */
                            game.setScreen<MenuScreen>()
                        }
                    }
                }
            }
            Gdx.input.inputProcessor = stage
        }
    }

    override fun render(delta: Float) {
        stage.run {
            act()
            draw()
        }
    }

    override fun hide() {
        super.hide()
        stage.clear()
    }

    override fun resize(width: Int, height: Int) {
        viewport.update(width, height, true)
    }

    override fun dispose() {
        super.dispose()
        mySkin.dispose()
    }
}
