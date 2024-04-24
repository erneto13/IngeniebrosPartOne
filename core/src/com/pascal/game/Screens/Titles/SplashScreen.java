package com.pascal.game.Screens.Titles;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.pascal.game.IngeniebrosGame;
import com.pascal.game.Screens.Game.PlayScreen;

public class SplashScreen implements Screen {
    private Texture splashTexture;
    private SpriteBatch batch;
    private final Game game;
    private float alpha;
    private float displayTime = 6.0f;
    private float blackScreenTime = 2; // Tiempo de espera en negro

    private boolean loadingNextScreen = false;

    public SplashScreen(IngeniebrosGame ingeniebrosGame) {
        this.game = ingeniebrosGame;
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        splashTexture = new Texture(Gdx.files.internal("splashs/SplashScreen.png"));
        alpha = 0f;
    }

    @Override
    public void render(float delta) {
        if (!loadingNextScreen) {
            displayTime -= delta;

            float fadeDuration = 1.5f;
            if (displayTime > 0 && alpha < 1.0f) {
                alpha = Math.min(1.0f, alpha + delta / fadeDuration);
            } else if (displayTime <= 0) {
                alpha = Math.max(0f, alpha - delta / fadeDuration);
                if (alpha <= 0) {
                    loadingNextScreen = true;
                }
            }
        } else {
            blackScreenTime -= delta; // Solo decrementa cuando no se está cargando la siguiente pantalla
            if (blackScreenTime <= 0) {
                game.setScreen(new PlayScreen(game));
                dispose();
                return;
            }
        }

        Gdx.gl.glClearColor(0, 0, 0, 1); // Fondo negro
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.setColor(1, 1, 1, alpha);
        batch.draw(splashTexture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.end();
    }

    @Override
    public void resize(int width, int height) {
        splashTexture.setFilter(Texture.TextureFilter.Linear, Texture.TextureFilter.Linear);
    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        batch.dispose();
        splashTexture.dispose();
    }
}
