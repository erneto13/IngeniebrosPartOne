package com.pascal.game.Screens.Game.IO;

import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
import com.pascal.game.Screens.Game.Options.MainOptionsScreen;
import com.pascal.game.Utils.RenderUtils;

public class InputsScreen implements InputProcessor {

    private boolean up = false, down = false;
    private boolean enter = false;
    private boolean click = false;

    private int mouseX = 0, mouseY = 0;

    MainOptionsScreen mainOptionsScreen;

    public InputsScreen(MainOptionsScreen mainOptionsScreen) {
        this.mainOptionsScreen = mainOptionsScreen;
    }

    @Override
    public boolean keyDown(int keycode) {
        mainOptionsScreen.victorTimely = 0.09;
        if (keycode == Keys.DOWN) {
            down = true;
        } else if (keycode == Keys.UP) {
            up = true;
        }

        if (keycode == Keys.ENTER) {
            enter = true;
        }
        return false;
    }

    @Override
    public boolean keyUp(int keycode) {
        if (keycode == Keys.DOWN) {
            down = false;
        }

        if (keycode == Keys.UP) {
            up = false;
        }

        if (keycode == Keys.ENTER) {
            enter = false;
        }
        return false;
    }

    @Override
    public boolean keyTyped(char character) {
        return false;
    }

    @Override
    public boolean touchDown(int screenX, int screenY, int pointer, int button) {
        click = true;
        return false;
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        click = false;
        return false;
    }

    @Override
    public boolean touchCancelled(int screenX, int screenY, int pointer, int button) {
        return false;
    }

    @Override
    public boolean touchDragged(int screenX, int screenY, int pointer) {
        return false;
    }

    @Override
    public boolean mouseMoved(int screenX, int screenY) {
        mouseX = screenX;
        mouseY = (RenderUtils.HEIGHT - screenY) - 1;
        return false;
    }

    @Override
    public boolean scrolled(float amountX, float amountY) {
        return false;
    }

    public boolean isUp() {
        return up;
    }

    public boolean isDown() {
        return down;
    }

    public boolean isEnter() {
        return enter;
    }

    public int getMouseX() {
        return mouseX;
    }

    public int getMouseY() {
        return mouseY;
    }

    public boolean isClick() {
        return click;
    }
}
