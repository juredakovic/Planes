package planes.screen

import com.badlogic.ashley.core.PooledEngine
import com.badlogic.gdx.graphics.g2d.SpriteBatch
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import ktx.app.clearScreen
import ecs.systems.active.BirdSpawnSystem
import ecs.systems.active.BoundsSystem
import ecs.systems.active.CollisionSystem
import ecs.systems.active.FlyingSystem
import ecs.systems.active.MovementSystem
import ecs.systems.active.PlaneInputSystem
import ecs.systems.active.RenderSystem
import ecs.systems.active.WorldWrapSystem
import ecs.systems.passive.EntityFactorySystem
import ecs.systems.passive.StartUpSystem
import planes.Planes

//private val camera : OrthographicCamera = OrthographicCamera().apply { setToOrtho(false, 800f, 480f) }
private val batch : SpriteBatch = SpriteBatch()
var engine : PooledEngine = PooledEngine()

class GameScreen(game: Planes, atlas: TextureAtlas) : AbstractPlanesScreen(game, atlas){

    override fun show() {

        engine.addSystem(EntityFactorySystem(atlas))
        engine.addSystem(StartUpSystem())
        engine.addSystem(MovementSystem())
        engine.addSystem(PlaneInputSystem())
        engine.addSystem(BirdSpawnSystem())
        engine.addSystem(FlyingSystem())
        engine.addSystem(BoundsSystem())
        engine.addSystem(WorldWrapSystem())
        engine.addSystem(CollisionSystem())
        engine.addSystem(RenderSystem())

    }

    override fun render(delta: Float) {
        clearScreen(0f,0f,0f,1f)

        engine.update(delta)

    }

    override fun dispose() {
        super.dispose()
        atlas.dispose()
        batch.dispose()
        engine.removeAllEntities()
    }
}
