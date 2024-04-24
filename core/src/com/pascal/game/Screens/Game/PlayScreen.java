package com.pascal.game.Screens.Game;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.pascal.game.Screens.Game.Extras.MainExtrasScreen;
import com.pascal.game.Screens.Game.IO.Inputs;
import com.pascal.game.Screens.Game.Options.MainOptionsScreen;
import com.pascal.game.Screens.Game.Play.MainPlayScreen;
import com.pascal.game.Utils.PathsUtils;
import com.pascal.game.Utils.TextUtils;

import static com.pascal.game.Utils.RenderUtils.batch;

public class PlayScreen implements Screen {

    static Game game;
    private final TextUtils[] textOptions = new TextUtils[4];
    private final String[] optionsMenu = {"MODO HISTORIA", "EXTRAS", "OPCIONES", "SALIR"};

    private final Inputs inputs = new Inputs(this);
    private int option = 1;
    private boolean optionMouse = false;
    public double victorTimely = 0;

    private Texture backgroundTexture;

    private TextUtils version;

    public PlayScreen(Game game) {
        PlayScreen.game = game;
    }

    @Override
    public void show() {
        backgroundTexture = new Texture(Gdx.files.internal("backgrounds/menu_background.png"));
        batch = new SpriteBatch();
        Gdx.input.setInputProcessor(inputs);
        version = new TextUtils(PathsUtils.THIRD_FONT, 22, Color.WHITE);

        // Generar los TextUtils para cada opción del menú
        for (int i = 0; i < optionsMenu.length; i++) {
            if (optionsMenu[i].equalsIgnoreCase("Opciones")) {
                textOptions[i] = new TextUtils(PathsUtils.THIRD_FONT, 48, Color.LIGHT_GRAY);
            } else if (optionsMenu[i].equalsIgnoreCase("Salir")) {
                textOptions[i] = new TextUtils(PathsUtils.THIRD_FONT, 48, Color.LIGHT_GRAY);
            } else {
                textOptions[i] = new TextUtils(PathsUtils.THIRD_FONT, 48, Color.WHITE);
            }

            // Configurar posición para todas las opciones
            float initialPositionX = ((float) Gdx.app.getGraphics().getWidth() / 2.5f - (textOptions[i].getWidth()));
            float initialPositionY = ((float) Gdx.app.getGraphics().getHeight() / 2 + textOptions[i].getHeight() / 2);
            float betweenSpace = textOptions[i].getHeight() + 30;

            textOptions[i].setText(optionsMenu[i]);
            if (textOptions[i] == textOptions[3]) {
                textOptions[i].setPosition(initialPositionX, initialPositionY - (betweenSpace * i + 25));
            } else if (textOptions[i] == textOptions[2]) {
                textOptions[i].setPosition(initialPositionX, initialPositionY - (betweenSpace * i + 25));
            } else {
                textOptions[i].setPosition(initialPositionX, initialPositionY - (betweenSpace * i));
            }
        }
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.draw(backgroundTexture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        version.setText("Version beta v1.0");
        version.setPosition(1200, 30);
        version.draw();

        // ciclo para mostrar las opciones
        for (TextUtils text : textOptions) {
            text.draw();
        }
        batch.end();

        // tiempo
        victorTimely += delta;

        // detección cuando es la tecla de la flechita para abajo
        if (inputs.isDown()) {
            if (victorTimely > 0.09f) {
                victorTimely = 0;
                option++;
                if (option > 4) {
                    option = 1;
                }
            }
        }

        // detección cuando es la tecla de la flechita para arriba
        if (inputs.isUp()) {
            if (victorTimely > 0.09f) {
                victorTimely = 0;
                option--;
                if (option < 1) {
                    option = 4;
                }
            }
        }

        // ciclo for para detectar el movimiento del mouse
        int count = 0;
        for (int i = 0; i < textOptions.length; i++) {
            if ((inputs.getMouseX() >= textOptions[i].getX()) && (inputs.getMouseX() <= (textOptions[i].getX() + textOptions[i].getWidth()))) {
                if ((inputs.getMouseY() >= textOptions[i].getY() - textOptions[i].getHeight()) && (inputs.getMouseY() <= (textOptions[i].getY()))) {
                    option = i + 1;
                    count++;
                }
            }
        }

        if (count > 0) {
            optionMouse = true;
        } else {
            optionMouse = false;
        }

        // cambiar el color del texto según la opción seleccionada
        for (int i = 0; i < textOptions.length; i++) {
            if (option == 1) {
                if (i == 0) {
                    textOptions[i].setTextColor(Color.YELLOW);
                } else if (i == 1) {
                    textOptions[i].setTextColor(Color.WHITE);
                } else if (i == 2) {
                    textOptions[i].setTextColor(Color.LIGHT_GRAY);
                } else {
                    textOptions[i].setTextColor(Color.LIGHT_GRAY);
                }
            } else if (option == 2) {
                if (i == 0) {
                    textOptions[i].setTextColor(Color.WHITE);
                } else if (i == 1) {
                    textOptions[i].setTextColor(Color.YELLOW);
                } else if (i == 2){
                    textOptions[i].setTextColor(Color.LIGHT_GRAY);
                } else {
                    textOptions[i].setTextColor(Color.LIGHT_GRAY);
                }
            } else if (option == 3) {
                if (i == 0 || i == 1) {
                    textOptions[i].setTextColor(Color.WHITE); // Opciones 1 y 2 en blanco
                } else if (i == 2) {
                    textOptions[i].setTextColor(Color.GRAY); // Opción 3 en gris
                } else if (i == 3) {
                    textOptions[i].setTextColor(Color.LIGHT_GRAY); // Opción 4 en rojo
                }
            } else if (option == 4) {
                if (i == 3) { // Para la opción "Salir" (índice 3)
                    textOptions[i].setTextColor(Color.RED); // Colorea de rosa
                } else { // Para las otras opciones
                    if (i == 0) {
                        textOptions[i].setTextColor(Color.WHITE); // Opción 1 en blanco
                    } else if (i == 1) {
                        textOptions[i].setTextColor(Color.WHITE); // Opción 2 en blanco
                    } else if (i == 2) {
                        textOptions[i].setTextColor(Color.LIGHT_GRAY); // Opción 3 en gris claro
                    }
                }
            }
        }

        // eventos a las pantallas
        if (inputs.isEnter() || (inputs.isClick())) {
            if (((option == 1) && (inputs.isEnter())) || ((option == 1) && (inputs.isClick()) && (optionMouse))) {
                game.setScreen(new MainPlayScreen(game));
            } else if (((option == 2) && (inputs.isEnter())) || ((option == 2) && (inputs.isClick()) && (optionMouse))) {
                game.setScreen(new MainExtrasScreen(game));
            } else if (((option == 3) && (inputs.isEnter())) || ((option == 3) && (inputs.isClick()) && (optionMouse))) {
                game.setScreen(new MainOptionsScreen(game));
            } else if (((option == 4) && (inputs.isEnter())) || ((option == 4) && (inputs.isClick()) && (optionMouse))) {
                Gdx.app.exit();
            }
        }
    }

    @Override
    public void resize(int width, int height) {
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
        backgroundTexture.dispose();
    }
}