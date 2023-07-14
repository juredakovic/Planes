package ecs.components

import com.badlogic.ashley.core.Component
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable
import ktx.ashley.mapperFor

class TextureComponent : Component {

    companion object {

        val TEXTUREMAPPER = mapperFor<TextureComponent>()
    }

    lateinit var texture : TextureRegionDrawable
}
