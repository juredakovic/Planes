package planes.screen

import com.badlogic.ashley.core.PooledEngine
import com.badlogic.gdx.graphics.OrthographicCamera
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import assetstorage.AssetPaths
import com.badlogic.gdx.audio.Sound
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.Texture
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import com.badlogic.gdx.utils.viewport.ExtendViewport
import com.badlogic.gdx.utils.viewport.FitViewport
import common.GameManager
import config.GameConfig
import ktx.app.clearScreen
import ecs.systems.active.BirdSpawnSystem
import ecs.systems.active.BoundsSystem
import ecs.systems.active.CollisionSystem
import ecs.systems.active.FlyingSystem
import ecs.systems.active.MovementSystem
import ecs.systems.active.ParcelSpawnSystem
import ecs.systems.active.PlaneInputSystem
import ecs.systems.active.PlaneSpawnSystem
import ecs.systems.active.RenderSystem
import ecs.systems.active.SkyscraperSpawnSystem
import ecs.systems.active.WorldWrapSystem
import ecs.systems.passive.EntityFactorySystem
import ecs.systems.passive.HudRenderSystem
import ecs.systems.passive.StartUpSystem
import ktx.ashley.getSystem
import ktx.assets.async.AssetStorage
import ktx.freetype.generateFont
import planes.Planes


class GameScreen(game: Planes, atlas: TextureAtlas) : AbstractPlanesScreen(game, atlas) {

    private val camera : OrthographicCamera = OrthographicCamera()
    private val  assetStorage : AssetStorage = game.getAssetStorage()
    private val batch : SpriteBatch = SpriteBatch()
    private val backgroundMusic : Sound = assetStorage.get<Sound>(AssetPaths.BACKGROUND_MUSIC)
    private var generator : FreeTypeFontGenerator = assetStorage.get<FreeTypeFontGenerator>(AssetPaths.FONT)
    private val backgroundTexture by lazy { Texture("gameAtlas/sky1.png") }

    private val fontA : BitmapFont = generator.generateFont {
        size = 40
        color = Color.FIREBRICK
    }

    private val fontB : BitmapFont = generator.generateFont {
        size = 30
        color = Color.FIREBRICK
    }

    private var engine : PooledEngine = PooledEngine()

    var viewport = FitViewport(GameConfig.WORLD_WIDTH, GameConfig.WORLD_HEIGHT, camera)
    private val hudViewPort = ExtendViewport(GameConfig.WORLD_WIDTH, GameConfig.WORLD_HEIGHT)
    lateinit var font : BitmapFont

    override fun show() {

            backgroundMusic.loop()

            font = fontA
            engine.addSystem(EntityFactorySystem(atlas))
            engine.addSystem(StartUpSystem())
            engine.addSystem(PlaneInputSystem())
            engine.addSystem(MovementSystem())
            engine.addSystem(WorldWrapSystem())
            engine.addSystem(BoundsSystem())
            engine.addSystem(BirdSpawnSystem())
            engine.addSystem(SkyscraperSpawnSystem())
            engine.addSystem(ParcelSpawnSystem())
            engine.addSystem(FlyingSystem())
            engine.addSystem(CollisionSystem())
            engine.addSystem(PlaneSpawnSystem())
            engine.addSystem(RenderSystem(batch, viewport, backgroundTexture))
            engine.addSystem(HudRenderSystem(batch, hudViewPort, font))



    }


    override fun render(delta: Float) {
         val renderSystem = engine.getSystem<RenderSystem>()
        if(GameManager.isGameOver()) {
            renderSystem.update(0f)
            backgroundMusic.stop()
            GameManager.resetResult()
            engine.removeAllEntities()
            engine.removeAllSystems()
            game.setScreen<GameOverScreen>()


        }else {
            clearScreen(0f,0f,0f,1f)
            engine.update(delta)
        }

        }


    override fun resize(width: Int, height: Int) {
        font = fontB
        engine.addSystem(HudRenderSystem(batch, hudViewPort,font))
        viewport.update(width, height, true)
        hudViewPort.update(width, height, true)
    }

    override fun hide() {
        super.hide()
    }

    override fun dispose() {
        super.dispose()
        atlas.dispose()
        batch.dispose()
        engine.removeAllEntities()
    }
}
