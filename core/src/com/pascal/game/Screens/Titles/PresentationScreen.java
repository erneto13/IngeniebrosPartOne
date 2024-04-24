package com.pascal.game.Screens.Titles;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.github.tommyettinger.textra.Font;
import com.github.tommyettinger.textra.TypingLabel;
import com.pascal.game.IngeniebrosGame;
import com.pascal.game.Screens.Game.PlayScreen;

public class PresentationScreen implements Screen {
    private Texture splashTexture;
    private SpriteBatch batch;
    private final Game game;
    private float alpha;
    private float displayTime = 8.0f;
    private float blackScreenTime = 2; // Tiempo de espera en negro

    private boolean loadingNextScreen = false;

    private Stage stage;
    private TypingLabel typingLabel;

    // Sonido de tecla
    private Sound keySound;
    private float keySoundTimer = 0;
    private int keySoundCounter = 0;

    public PresentationScreen(Game game) {
        this.game = game;
    }

    @Override
    public void show() {
        batch = new SpriteBatch();
        splashTexture = new Texture(Gdx.files.internal("splashs/PresentationScreen.png"));
        alpha = 0f;

        stage = new Stage(new ScreenViewport());
        Table root = new Table();
        root.setFillParent(true);
        stage.addActor(root);

        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("fonts/Bitfantasy.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();

        parameter.size = 45;
        parameter.color = Color.WHITE;
        Font font = new Font(generator.generateFont(parameter));

        typingLabel = new TypingLabel("[WHITE]{SPEED=0.3}PRESENTA", font);

        // Obtén el ancho del texto
        float textWidth = typingLabel.getPrefWidth();

        // Calcula la posición horizontal para centrar el texto en la pantalla
        float centerX = (Gdx.graphics.getWidth() - textWidth) / 2;

        typingLabel.setPosition(centerX, (float) Gdx.graphics.getHeight() / 2.3f);
        root.addActor(typingLabel);

        // cargar el sonido de tecleo
        keySound = Gdx.audio.newSound(Gdx.files.internal("sounds/keyboard.wav"));
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

            // Temporizador para reproducir el sonido de la tecla cada 0.3 segundos
            keySoundTimer += delta;
            if (keySoundTimer >= 0.1f && keySoundCounter < 7) {
                keySound.play();
                keySoundTimer = 0;
                keySoundCounter++;
            }
        } else {
            blackScreenTime -= delta; // Solo decrementa cuando no se está cargando la siguiente pantalla
            if (blackScreenTime <= 0) {
                game.setScreen(new SplashScreen((IngeniebrosGame) game));
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

        typingLabel.getColor().a = alpha;

        stage.act();
        stage.draw();
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
        keySound.dispose();
    }
}