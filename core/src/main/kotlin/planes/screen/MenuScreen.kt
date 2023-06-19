package planes.screen

import assetstorage.RegionNames.Companion.BACKGROUND
import com.badlogic.gdx.Gdx
import ktx.scene2d.*
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.scenes.scene2d.Stage
import com.badlogic.gdx.scenes.scene2d.ui.Skin
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable

import com.badlogic.gdx.utils.Align
import ktx.actors.onClick
import planes.Planes

class MenuScreen(game : Planes, atlas: TextureAtlas) : AbstractPlanesScreen(game, atlas) {

private lateinit var stage : Stage
private val mySkin : Skin = Skin(Gdx.files.internal("skin/glassy-ui.json"))

override fun show() {

    stage = Stage()
    Scene2DSkin.defaultSkin = mySkin
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
                        onClick { game.setScreen<GameScreen>() }
                    }
                    button(style = "small") {
                        label("Options") {
                        }
                    }
                    button(style = "small") {
                        label("Exit")
                    }
                }
            }
        }

    Gdx.input.inputProcessor = stage
}
override fun render(delta : Float) {
    stage.act()
    stage.draw()
}

    override fun dispose() {
        super.dispose()
        mySkin.dispose()
        stage.dispose()
    }

}
