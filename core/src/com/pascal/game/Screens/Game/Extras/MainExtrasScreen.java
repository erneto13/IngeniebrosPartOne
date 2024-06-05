package com.pascal.game.Screens.Game.Extras;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;

import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.pascal.game.IngeniebrosGame;
import com.pascal.game.Screens.Game.Extras.MainExtrasScreen;
import com.pascal.game.Screens.Game.IO.Inputs;
import com.pascal.game.Managers.PreferencesManager;
import com.pascal.game.Screens.Game.IO.InputsScreen;
import com.pascal.game.Screens.Game.Options.MainOptionsScreen;
import com.pascal.game.Screens.Game.Play.MainPlayScreen;
import com.pascal.game.Screens.Game.PlayScreen;
import com.pascal.game.Utils.PathsUtils;
import com.pascal.game.Utils.TextUtils;

import static com.pascal.game.Utils.RenderUtils.batch;


public class MainExtrasScreen implements Screen {
    private boolean optionMouse = false;
    static Game game;
    private final InputsScreen inputsOptions = new InputsScreen(this);
    private Stage stage;
    private Texture backgroundTexture;
    private TextUtils BACK_MENU;
    public MainExtrasScreen(Game game) {
        this.game = game;

    }

    @Override
    public void show() {
        stage = new Stage(new ScreenViewport());
        Gdx.input.setInputProcessor(stage);

        backgroundTexture = new Texture(Gdx.files.internal("backgrounds/clouds_background.png"));

        Skin skin = new Skin(Gdx.files.internal("uiskin.json"));
        BitmapFont font = new BitmapFont();
        skin.add("default", font);

        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = font;
        labelStyle.fontColor = Color.WHITE;

        Label messageLabel = new Label("Aún no hay contenido extra, vuelva después", labelStyle);

        BACK_MENU = new TextUtils(PathsUtils.THIRD_FONT, 35, Color.WHITE);
        BACK_MENU.setText("REGRESAR");
        BACK_MENU.setPosition(40, 80);

        Table table = new Table();
        table.setFillParent(true);
        table.top();
        table.add(messageLabel).padTop(20);
        table.row();
        stage.addActor(table);

    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        batch.begin();
        batch.draw(backgroundTexture, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        BACK_MENU.draw(); // dibujar la opcion de regresar
        stage.act(Math.min(Gdx.graphics.getDeltaTime(), 1 / 30f));
        stage.draw();
        batch.end();
        handleBackOption();
    }

    @Override
    public void resize(int width, int height) {
        stage.getViewport().update(width, height, true);

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
        stage.dispose();
        backgroundTexture.dispose();
    }
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
