package planes


import assetstorage.AssetDescriptors.Companion.BACKGROUND_MUSIC

import assetstorage.AssetDescriptors.Companion.GAME_ATLAS
import assetstorage.AssetPaths
import com.badlogic.gdx.graphics.g2d.BitmapFont
import ktx.app.KtxGame
import ktx.app.KtxScreen
import com.badlogic.gdx.graphics.g2d.TextureAtlas
import kotlinx.coroutines.launch
import ktx.assets.async.AssetStorage
import ktx.async.KtxAsync
import ktx.freetype.async.loadFreeTypeFont
import com.badlogic.gdx.assets.loaders.resolvers.InternalFileHandleResolver
import com.badlogic.gdx.audio.Sound
import ktx.freetype.async.registerFreeTypeFontLoaders
import planes.screen.GameScreen
import planes.screen.MenuScreen
import com.badlogic.gdx.graphics.Color
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator
import planes.screen.GameOverScreen

import planes.screen.OptionsScreen
import planes.screen.ScoreScreen
import planes.screen.UserScreen

class Planes() : KtxGame<KtxScreen>() {

    private val planesRef = this

    private lateinit var assetStorage : AssetStorage
    override fun create() {

        assetStorage = AssetStorage(fileResolver = InternalFileHandleResolver())

        KtxAsync.initiate()
        assetStorage.registerFreeTypeFontLoaders(replaceDefaultBitmapFontLoader = true)

        KtxAsync.launch {

            assetStorage.apply {

                //val preferences : Preferences by lazy { Gdx.app.getPreferences("Planes") }
                val atlas : TextureAtlas = assetStorage.load(GAME_ATLAS)
                val backgroundMusic : Sound = assetStorage.load(BACKGROUND_MUSIC)
                var generator : FreeTypeFontGenerator = assetStorage.load<FreeTypeFontGenerator>(AssetPaths.FONT)
                val font : BitmapFont = assetStorage.loadFreeTypeFont(AssetPaths.FONT) {
                    size = 50
                    color = Color.FIREBRICK
                }

                addScreen(MenuScreen(planesRef, atlas))
                addScreen(GameScreen(planesRef, atlas))
                addScreen(OptionsScreen(planesRef, atlas))
                addScreen(ScoreScreen(planesRef, atlas))
                addScreen(GameOverScreen(planesRef, atlas))
                addScreen(UserScreen(planesRef, atlas))
                /*setScreen<ScoreScreen>()*/
                setScreen<UserScreen>()
                //setScreen<MenuScreen>()
            }
        }
    }

    override fun dispose() {
        super.dispose()
        assetStorage.dispose()

    }
    fun getAssetStorage() : AssetStorage {
        return assetStorage
    }
}
