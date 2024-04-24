package com.pascal.game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Cursor;
import com.badlogic.gdx.graphics.Pixmap;
import com.pascal.game.Screens.Game.PlayScreen;

public class IngeniebrosGame extends Game {

    @Override
    public void create() {
        Pixmap cursor = new Pixmap(Gdx.files.internal("ui/custom_cursor.png"));

        Cursor customCursor = Gdx.graphics.newCursor(cursor, 0, 0);
        Gdx.graphics.setCursor(customCursor);
        setScreen(new PlayScreen(this));

        // Liberar la memoria de la textura del puntero del mouse
        cursor.dispose();
    }

    @Override
    public void render() {
        super.render();
    }

    @Override
    public void dispose() {
        super.dispose();
    }
}
