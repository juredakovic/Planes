package com.ktx.planes;

import com.badlogic.gdx.Game;

/** {@link com.badlogic.gdx.ApplicationListener} implementation shared by all platforms. */
public class Planes extends Game {
    @Override
    public void create() {
        setScreen(new FirstScreen());
    }
}