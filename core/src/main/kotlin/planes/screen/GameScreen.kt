package planes.screen

import assetstorage.RegionNames.Companion.BIRD
import assetstorage.RegionNames.Companion.GAME_BACKGROUND
import assetstorage.RegionNames.Companion.PLANE
import com.badlogic.gdx.Gdx
import com.badlogic.gdx.Input
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import com.badlogic.gdx.math.MathUtils
import com.badlogic.gdx.math.Rectangle
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import ktx.app.clearScreen
import ktx.graphics.use
import com.badlogic.gdx.utils.TimeUtils
import ktx.collections.*
import planes.Planes

private val camera : OrthographicCamera = OrthographicCamera().apply { setToOrtho(false, 800f, 480f) }
private val batch : SpriteBatch = SpriteBatch()
private var switch : Boolean = false
private val birds = gdxArrayOf<Rectangle>()
private val planeRectangle : Rectangle = Rectangle()
private const val CREATE_BIRD_TIME  = 1000000000
private var lastBirdSpawnTime : Long = TimeUtils.nanoTime()

class GameScreen(game: Planes, atlas: TextureAtlas) : AbstractPlanesScreen(game, atlas){

    private val planeImage = TextureRegionDrawable(atlas.findRegion(PLANE))
    private val birdImage =  TextureRegionDrawable(atlas.findRegion(BIRD))
    private val backgroundImage = TextureRegionDrawable(atlas.findRegion(GAME_BACKGROUND))

    override fun show() {
        planeRectangle.x = 15f
        planeRectangle.y = (Gdx.graphics.height / 2f - planeImage.region.regionWidth / 2f)
        planeRectangle.width = planeImage.region.regionHeight.toFloat()
        planeRectangle.height = planeImage.region.regionHeight.toFloat()
        spawnBird(birdImage)
    }

    override fun render(delta: Float) {
        clearScreen(0f,0f,0f,1f)
        planeRectangle.y -= 0.981f * 1/2

        when {Gdx.input.isKeyPressed(Input.Keys.LEFT) -> moveLeft()}
        when {Gdx.input.isKeyPressed(Input.Keys.RIGHT) -> moveRight(planeImage) }
        when{ Gdx.input.isKeyPressed(Input.Keys.UP) -> moveUp()}
        when {Gdx.input.isKeyPressed(Input.Keys.DOWN) -> moveDown()}

        camera.update()

        if(TimeUtils.nanoTime() - lastBirdSpawnTime > CREATE_BIRD_TIME){
            spawnBird(birdImage)
        }

        batch.use{
            it.draw(backgroundImage.region, 0f, 0f, Gdx.graphics.width.toFloat(), Gdx.graphics.height.toFloat())
            it.draw(planeImage.region, planeRectangle.x, planeRectangle.y)
            for(bird : Rectangle in birds) {
                it.draw(birdImage.region, bird.x, bird.y)
            }
        }

        for(bird : Rectangle in birds) {
            bird.x -= (100 * Gdx.graphics.deltaTime)

            if(switch) {
                bird.y += (100 *  Gdx.graphics.deltaTime.toDouble()).toFloat()
                switch = !switch
            } else {
                bird.y -=  (100 *  Gdx.graphics.deltaTime.toDouble()).toFloat()
                switch = !switch
            }

            when{ bird.overlaps(planeRectangle) -> birds -= bird}
            when {bird.y + bird.height > Gdx.graphics.height -> birds -= bird}
            when{ bird.y + birdImage.region.regionHeight.toFloat() < 0 -> birds -= bird}
            when{ bird.x + birdImage.region.regionHeight.toFloat() < 0 -> birds -= bird}

        }
    }

    override fun dispose() {
        super.dispose()
        atlas.dispose()
        batch.dispose()
    }

}

private fun spawnBird(birdImage : TextureRegionDrawable) {
    val birdRectangle = Rectangle()
    birdRectangle.y = MathUtils.random(0f, Gdx.graphics.height.toFloat() - birdImage.region.regionHeight.toFloat())
    birdRectangle.x = Gdx.graphics.width.toFloat()
    birdRectangle.width = birdImage.region.regionWidth.toFloat()
    birdRectangle.height = birdImage.region.regionHeight.toFloat()
    birds += birdRectangle
    lastBirdSpawnTime = TimeUtils.nanoTime()
}

private fun moveRight(planeImage : TextureRegionDrawable)  {
    planeRectangle.x += 250 * 0.981f * 1/2 * Gdx.graphics.deltaTime

    if(planeRectangle.x + planeRectangle.width > Gdx.graphics.width) {
        planeRectangle.x = (Gdx.graphics.width - planeImage.region.regionWidth).toFloat()
    }
}

private fun moveLeft()  {
    planeRectangle.x -= 250 * 0.981f * 1/2 *  Gdx.graphics.deltaTime

    if(planeRectangle.x < 0) {
        planeRectangle.x = 0f
    }
}

private fun moveUp() {
    planeRectangle.y += 250 * 0.981f * 1/2 *  Gdx.graphics.deltaTime

    if(planeRectangle.y + planeRectangle.height > Gdx.graphics.height) {
        planeRectangle.y = Gdx.graphics.height.toFloat() - planeRectangle.height
    }
}

private fun moveDown() {
    planeRectangle.y -= 250 * Gdx.graphics.deltaTime

    if(planeRectangle.y < 0) {
        planeRectangle.y = 0f
    }
}
