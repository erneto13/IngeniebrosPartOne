package com.pascal.game.Screens.Game.Play;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.Drawable;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.pascal.game.Conversations.ConversationHandler;
import com.pascal.game.Entities.Player;
import com.pascal.game.Entities.NPC;

import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class MainPlayScreen implements Screen {

    private TiledMap map;
    SpriteBatch batch;
    Texture img;
    private OrthogonalTiledMapRenderer renderer;
    private OrthographicCamera camera;
    private Player player;
    private NPC npc;
    Label messageLabel;

    // intefaz
    private Stage stage;
    private Skin skin;
    private ConversationHandler conversationHandler;

    public MainPlayScreen(Game game) {
        // Inicializar Stage y Skin aquí
        stage = new Stage(new ScreenViewport());
        skin = new Skin();
        skin.add("default", new BitmapFont());
        // Crear un Pixmap para el fondo del Window
        Pixmap pixmap = new Pixmap(1, 1, Pixmap.Format.RGBA8888);
        pixmap.setColor(Color.DARK_GRAY);
        pixmap.fill();
        Drawable drawable = new TextureRegionDrawable(new Texture(pixmap));
        pixmap.dispose();

        // Crear y añadir el estilo de Window a Skin
        Window.WindowStyle windowStyle = new Window.WindowStyle();
        windowStyle.background = drawable;
        windowStyle.titleFont = skin.getFont("default");
        skin.add("default", windowStyle);

        // Crear un estilo de TextButton y Label
        TextButton.TextButtonStyle textButtonStyle = new TextButton.TextButtonStyle();
        textButtonStyle.font = skin.getFont("default");
        skin.add("default", textButtonStyle);

        Label.LabelStyle labelStyle = new Label.LabelStyle();
        labelStyle.font = skin.getFont("default");
        skin.add("default", labelStyle);
    }

    @Override
    public void show() {
        TmxMapLoader loader = new TmxMapLoader();
        map = loader.load("Maps/miado.tmx");
        renderer = new OrthogonalTiledMapRenderer(map);
        camera = new OrthographicCamera();
        player = new Player(new Sprite(new Texture("sprite1.png")), (TiledMapTileLayer) map.getLayers().get(1));
        messageLabel = new Label("Interactuar",skin);
        // Cargar y crear el NPC
        npc = new NPC(new Sprite(new Texture("npc_sprite.png")), (TiledMapTileLayer) map.getLayers().get(1),"Figueroa");
        npc.setPosition(100, 100);  // Establece la posición inicial del NPC
        conversationHandler = new ConversationHandler(skin, stage);

        camera.zoom = .8f;

        // Establecer el InputProcessor para Stage y Player
        Gdx.input.setInputProcessor(new InputMultiplexer(stage, player));
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        camera.position.set(player.getX() + player.getWidth() / 2, player.getY() + player.getHeight() / 2, 0);
        camera.update();
        renderer.setView(camera);
        renderer.render();

        renderer.getBatch().begin();
        player.draw(renderer.getBatch());
        npc.draw(renderer.getBatch());
        renderer.getBatch().end();

        checkPlayerNPCInteraction();
        // Dibujar la UI
        stage.act(Gdx.graphics.getDeltaTime());
        stage.draw();
    }
    private void updateMessagePosition(){
        float labelX = player.getX();
        float labelY = player.getY() + 10;
        messageLabel.setPosition(labelX,labelY);
    }
    private void checkPlayerNPCInteraction() {
        float distanceX = Math.abs(player.getX() - npc.getX());
        float distanceY = Math.abs(player.getY() - npc.getY());
        if (distanceX < 20 && distanceY < 20) {
            //Aca el coso de arriba
            stage.addActor(messageLabel);
            messageLabel.setPosition(100,300);
            if(Gdx.input.isKeyPressed(Input.Keys.E)){
                handleInteraction();
            }


        }else{
            messageLabel.remove();
        }
    }

    private void handleInteraction() {
        conversationHandler.showNPCInteractionDialog(npc.getNombre());
    }

    @Override
    public void resize(int width, int height) {
        camera.viewportWidth = width;
        camera.viewportHeight = height;
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
        map.dispose();
        renderer.dispose();
        player.getTexture().dispose();
        stage.dispose();
        skin.dispose();
    }
}
