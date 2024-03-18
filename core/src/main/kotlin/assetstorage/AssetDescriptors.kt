package assetstorage

import com.badlogic.gdx.assets.AssetDescriptor
import com.badlogic.gdx.audio.Sound
import com.badlogic.gdx.graphics.g2d.BitmapFont
import com.badlogic.gdx.graphics.g2d.TextureAtlas

class AssetDescriptors {

    companion object {
        val BACKGROUND_MUSIC: AssetDescriptor<Sound> = AssetDescriptor(AssetPaths.BACKGROUND_MUSIC, Sound::class.java)
        val GAME_ATLAS : AssetDescriptor<TextureAtlas> =
            AssetDescriptor(AssetPaths.GAME_ATLAS, TextureAtlas::class.java)
        val FONT : AssetDescriptor<BitmapFont> = AssetDescriptor(AssetPaths.FONT, BitmapFont::class.java)
    }

}
