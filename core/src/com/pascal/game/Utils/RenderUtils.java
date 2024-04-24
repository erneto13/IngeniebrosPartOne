package com.pascal.game.Utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class RenderUtils {
    public static SpriteBatch batch;
    public static int WIDTH = (int) Float.parseFloat(String.valueOf(Gdx.graphics.getWidth()));
    public static int HEIGHT = (int) Float.parseFloat(String.valueOf(Gdx.graphics.getHeight()));
}
