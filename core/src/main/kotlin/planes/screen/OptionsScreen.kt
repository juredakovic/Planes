package planes.screen

import assetstorage.RegionNames
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import com.badlogic.gdx.utils.Align
import com.badlogic.gdx.utils.viewport.ExtendViewport
import com.badlogic.gdx.utils.viewport.Viewport
import common.GameManager
import ktx.actors.onClick
import ktx.scene2d.Scene2DSkin
import ktx.scene2d.actors
import ktx.scene2d.button
import ktx.scene2d.label
import ktx.scene2d.table
import ktx.scene2d.verticalGroup
import planes.Planes

class OptionsScreen(game : Planes, atlas : TextureAtlas) : AbstractPlanesScreen(game, atlas) {

    private lateinit var stage : Stage
    private val mySkin : Skin = Skin(Gdx.files.internal("skin/glassy-ui.json"))
    private var viewport : Viewport = ExtendViewport(Gdx.graphics.width.toFloat(), Gdx.graphics.height.toFloat())

    override fun show() {
        stage = Stage(viewport)
        Scene2DSkin.defaultSkin = mySkin

        stage.actors {
            table {
                background = TextureRegionDrawable(atlas.findRegion(RegionNames.BACKGROUND))
                setFillParent(true)
                align(Align.center)

                verticalGroup {
                    space(25f)
                    button(style = "small") {
                        label("Easy") {
                        }
                        onClick {
                            GameManager.setDifficulty("easy")
                            game.setScreen<GameScreen>()
                        }
                    }

                    button(style = "small") {
                        label("Medium") {
                        }
                        onClick {
                            GameManager.setDifficulty("medium")
                            game.setScreen<GameScreen>()
                        }
                    }

                    button(style = "small") {
                        label("Hard") {
                        }
                        onClick {
                            GameManager.setDifficulty("hard")
                            game.setScreen<GameScreen>()
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

    override fun dispose() {
        super.dispose()
        mySkin.dispose()
    }
}
