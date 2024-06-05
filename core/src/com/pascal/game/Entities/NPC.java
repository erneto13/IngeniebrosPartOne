package com.pascal.game.Entities;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

public class NPC extends Sprite {
    private TiledMapTileLayer collisionLayer;

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public float getVida() {
        return vida;
    }

    public void setVida(float vida) {
        this.vida = vida;
    }
    private String nombre;
    private int nivel = 1;
    private float vida=100;
    public NPC(Sprite sprite, TiledMapTileLayer collisionLayer) {
        super(sprite);
        this.collisionLayer = collisionLayer;
    }
    public NPC(Sprite sprite, TiledMapTileLayer collisionLayer, String nombre) {
        super(sprite);
        this.collisionLayer = collisionLayer;
        this.nombre = nombre;
    }
    public void update(float delta) {
        // Actualización lógica del NPC si es necesario
    }
}
