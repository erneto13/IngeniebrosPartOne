package com.pascal.game.Utils;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.*;
import com.badlogic.gdx.math.Vector2;

public class TextUtils {
    BitmapFont font;
    private GlyphLayout glyphLayout;
    public float x = 0, y = 0;
    private String text = "";

    public TextUtils(String fontPath, int fontSize, Color color) {
        FreeTypeFontGenerator fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal(fontPath));
        FreeTypeFontParameter fontParameter = new FreeTypeFontParameter();

        fontParameter.size = fontSize;
        fontParameter.color = color;

        font = fontGenerator.generateFont(fontParameter);
        glyphLayout = new GlyphLayout();
    }

    public void draw() {
        font.draw(RenderUtils.batch, text, x, y);
    }

    public void setTextColor(Color color) {
        font.setColor(color);
    }

    public void setPosition(float x, float y) {
        this.x = x;
        this.y = y;
    }

    public float getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public float getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
        glyphLayout.setText(font, text);
    }

    public float getWidth() {
        return glyphLayout.width;
    }

    public float getHeight() {
        return glyphLayout.height;
    }

    public Vector2 getDimension() {
        return new Vector2(glyphLayout.width, glyphLayout.height);
    }

    public Vector2 getPosition() {
        return new Vector2(x, y);
    }
}
