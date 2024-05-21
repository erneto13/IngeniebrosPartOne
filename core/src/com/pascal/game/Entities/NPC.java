package com.pascal.game.Entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

public class NPC extends Sprite {
    private TiledMapTileLayer collisionLayer;

    public NPC(Sprite sprite, TiledMapTileLayer collisionLayer) {
        super(sprite);
        this.collisionLayer = collisionLayer;
    }

    public void update(float delta) {
        // Actualización lógica del NPC si es necesario
    }
}
