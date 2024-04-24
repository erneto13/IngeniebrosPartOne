package com.pascal.game.Screens.Game.Options;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.pascal.game.Screens.Game.IO.InputsScreen;
import com.pascal.game.Screens.Game.Play.MainPlayScreen;
import com.pascal.game.Screens.Game.PlayScreen;
import com.pascal.game.Utils.PathsUtils;
import com.pascal.game.Utils.TextUtils;

import static com.pascal.game.Utils.RenderUtils.batch;

public class MainOptionsScreen implements Screen {

    public static Game game;

    private TextUtils BACK_MENU;

    // para usar el mouse
    private final InputsScreen inputsOptions = new InputsScreen(this);
    public double victorTimely = 0;
    private int option = 1;

    // constructor de la clase
    public MainOptionsScreen(Game game) {
        this.game = game;
    }

    @Override
    public void show() {
        // inicialización del render
        batch = new SpriteBatch();

        // indica a la clase quien se va a encargar de procesar las entradas
        Gdx.input.setInputProcessor(inputsOptions);

        // inicialización del texto "BACK"
        BACK_MENU = new TextUtils(PathsUtils.THIRD_FONT, 35, Color.WHITE);
        BACK_MENU.setText("REGRESAR");
        BACK_MENU.setPosition(40, 80);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        BACK_MENU.draw(); // dibujar la opcion de regresar

        batch.end();
        handleBackOption();

        victorTimely += delta;
    }

    @Override
    public void resize(int width, int height) {}

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {
        batch.dispose();
    }

    // función para el botón de regresar
    private void handleBackOption() {
        // Verificar si el mouse está sobre la opción de regresar
        if (inputsOptions.getMouseX() >= BACK_MENU.getX() &&
                inputsOptions.getMouseX() <= BACK_MENU.getX() + BACK_MENU.getWidth() &&
                inputsOptions.getMouseY() >= BACK_MENU.getY() - BACK_MENU.getHeight() &&
                inputsOptions.getMouseY() <= BACK_MENU.getY()) {
            // Cambiar el color del texto de regresar
            BACK_MENU.setTextColor(Color.LIGHT_GRAY);

            // Si se hace clic en la opción de regresar
            if (inputsOptions.isClick()) {
                batch.dispose();
                game.setScreen(new PlayScreen(game));
            }
        } else {
            // Si el mouse no está sobre la opción de regresar, mantener el color blanco
            BACK_MENU.setTextColor(Color.WHITE);
        }
    }

}
