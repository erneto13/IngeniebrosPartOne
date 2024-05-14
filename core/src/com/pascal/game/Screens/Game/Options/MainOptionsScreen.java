package com.pascal.game.Screens.Game.Options;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.pascal.game.Managers.PreferencesManager;
import com.pascal.game.Screens.Game.IO.InputsScreen;
import com.pascal.game.Screens.Game.PlayScreen;
import com.pascal.game.Utils.PathsUtils;
import com.pascal.game.Utils.TextUtils;

import static com.pascal.game.Utils.RenderUtils.batch;

public class MainOptionsScreen implements Screen {

    public static Game game;
    private Stage stage;
    private Skin skin;

    // Elements
    private Label volumeMusicLabel;
    private Label musicOnOffLabel;
    private Slider volumeMusicSlider;
    private CheckBox musicCheckbox;

    // AppPreferences
    private PreferencesManager preferencesManager;
    private InputMultiplexer inputMultiplexer;

    // Texto y fondo de la pantalla
    private TextUtils BACK_MENU;
    private Texture background;

    // Para el uso del mouse
    private final InputsScreen inputsOptions = new InputsScreen(this);
    public double victorTimely = 0;
    private int option = 1;

    // Constructor de la clase
    public MainOptionsScreen(Game game) {
        this.game = game;
        this.stage = new Stage();
        this.skin = new Skin(Gdx.files.internal("uiskin.json"));
        this.preferencesManager = new PreferencesManager();
        this.inputMultiplexer = new InputMultiplexer(); // Aquí se inicializa inputMultiplexer
        inputMultiplexer.addProcessor(inputsOptions);
        inputMultiplexer.addProcessor(stage);
    }

    @Override
    public void show() {
        // inicialización del render
        batch = new SpriteBatch();
        background = new Texture(Gdx.files.internal("backgrounds/clouds_background.png"));

        // indica a la clase quien se va a encargar de procesar las entradas
        Gdx.input.setInputProcessor(inputMultiplexer);

        // inicialización del texto "BACK"
        BACK_MENU = new TextUtils(PathsUtils.THIRD_FONT, 35, Color.WHITE);
        BACK_MENU.setText("REGRESAR");
        BACK_MENU.setPosition(40, 80);

        // Create elements
        volumeMusicLabel = new Label("Volumen de la musica", skin);
        musicOnOffLabel = new Label("Musica", skin);
        volumeMusicSlider = new Slider(0f, 100f, 1f, false, skin);
        musicCheckbox = new CheckBox(null, skin);

        // Set initial values from preferences
        volumeMusicSlider.setValue(preferencesManager.getMusicVolume() * 100f); // Multiplica por 100 para convertirlo al rango del slider (0-100)
        musicCheckbox.setChecked(preferencesManager.isMusicEnabled());

        // Add listeners
        volumeMusicSlider.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                float volume = volumeMusicSlider.getValue() / 100f; // Ajusta el valor del deslizador entre 0 y 1
                preferencesManager.setMusicVolume(volume);
                // Actualiza el volumen de la música en tiempo real
                if (PlayScreen.music != null) {
                    PlayScreen.music.setVolume(volume);
                }
            }
        });

        musicCheckbox.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                boolean enabled = musicCheckbox.isChecked();
                preferencesManager.setMusicEnabled(enabled);
                if (enabled) {
                    // Si la música está activada, inicia la reproducción de la música si no se está reproduciendo actualmente
                    if (PlayScreen.music != null && !PlayScreen.music.isPlaying()) {
                        PlayScreen.music.play();
                    }
                } else {
                    // Si la música está desactivada, detén la reproducción de la música si se está reproduciendo actualmente
                    if (PlayScreen.music != null && PlayScreen.music.isPlaying()) {
                        PlayScreen.music.stop();
                    }
                }
            }
        });


        // Create layout
        Table table = new Table();
        table.setFillParent(true);
        table.defaults().pad(10);

        // Add elements to table
        table.row();
        table.add(volumeMusicLabel).left();
        table.add(volumeMusicSlider).fillX().expandX();
        table.row();
        table.add(musicOnOffLabel).left();
        table.add(musicCheckbox).left();
        table.row();

        stage.addActor(table);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.begin();
        batch.draw(background, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        BACK_MENU.draw(); // dibujar la opcion de regresar
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
        batch.end();
        handleBackOption();

        // Guardar el valor del volumen de la música en las preferencias cada vez que se modifique el slider
        if (volumeMusicSlider.isDragging()) {
            float volume = volumeMusicSlider.getValue() / 100f; // Ajusta el valor del slider al rango (0-1)
            preferencesManager.setMusicVolume(volume);
            if (PlayScreen.music != null) {
                PlayScreen.music.setVolume(volume);
            }
        }

        victorTimely += delta;
    }


    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);
    }

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {
        batch.dispose();
        stage.dispose();
        skin.dispose();
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
