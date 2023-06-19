package assetstorage

import com.badlogic.gdx.assets.AssetDescriptor
import com.badlogic.gdx.graphics.g2d.TextureAtlas

class AssetDescriptors {

    companion object {
        val GAME_ATLAS : AssetDescriptor<TextureAtlas> =
            AssetDescriptor(AssetPaths.GAME_ATLAS, TextureAtlas::class.java)
    }
}
