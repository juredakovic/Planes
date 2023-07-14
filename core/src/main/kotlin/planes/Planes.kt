package planes


import assetstorage.AssetDescriptors.Companion.GAME_ATLAS
import ktx.app.KtxGame
import ktx.app.KtxScreen
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import kotlinx.coroutines.launch
import ktx.assets.async.AssetStorage
import ktx.async.KtxAsync


import planes.screen.GameScreen
import planes.screen.MenuScreen

/** [com.badlogic.gdx.ApplicationListener] implementation shared by all platforms.  */
class Planes() : KtxGame<KtxScreen>() {

    private val planesRef = this

    override fun create() {

        val assetStorage = AssetStorage()
        KtxAsync.initiate()
        KtxAsync.launch {
            assetStorage.apply {
                val atlas : TextureAtlas = assetStorage.load(GAME_ATLAS)
                addScreen(MenuScreen(planesRef, atlas))
                addScreen(GameScreen(planesRef, atlas))
                setScreen<MenuScreen>()
            }
        }

    }

}
