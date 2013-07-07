package com.example.libgdxtestgame;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.GL10;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

import java.awt.*;

/**
 * Created with IntelliJ IDEA.
 * User: stephen
 * Date: 6/3/13
 * Time: 4:04 PM
 * To change this template use File | Settings | File Templates.
 */
public class LibGdxGame implements ApplicationListener {
    Music bgMusic;

    Map testmap;

    Rectangle character;

    PCharacter duder;

    public static int scrnWidth, scrnHeight;
    public static float scaleW, scaleH;

    TextureCache txtCache = TextureCache.getInstance();

    OrthographicCamera camera;
    SpriteBatch spritebatch;

    @Override
    public void create() {
        testmap = new Map("testmap.txt", "Test");

        duder = new PCharacter("main", "smiley_sprite");

        scrnHeight = 160;
        scrnWidth = 144;
        scaleW = scaleH = 1f;

        //bgMusic.setLooping(true);
        //bgMusic.play();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, scrnWidth, scrnHeight);

        spritebatch = new SpriteBatch();
    }

    @Override
    public void resize(int width, int height) {
        scrnHeight = height;
        scrnWidth = width;
        scaleH = scrnHeight / 160f;
        scaleW = scrnWidth / 144f;
        System.out.println("Resized to " + scrnHeight + "x" + scrnWidth);
        System.out.println("New scale factors of " + scaleH + " and " + scaleW);

    }

    @Override
    public void render() {
        //To change body of implemented methods use File | Settings | File Templates.
        Gdx.gl.glClearColor(0, 0, 0.6f, 1);
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);


        //bubble.x += 1;
        //bubble.y += 1;

        centerCamera();
        camera.update();

        spritebatch.setProjectionMatrix(camera.combined);
        spritebatch.begin();
        int x = 0, y = 0;
        for (String row : testmap.mapMatrix) {
            for (Character tileC : row.toCharArray()) {
                TextureNode tile = txtCache.loadTexture(Map.intToTileIdent(tileC));
                spritebatch.draw(tile.texture, x, y);
                x += 16;
            }
            x = 0;
            y += 16;
        }
        duder.move();
        spritebatch.draw(txtCache.loadTexture(PCharacter.fileExt(duder.spriteID)).texture, duder.currentPos.x, duder.currentPos.y);
        //spritebatch.draw(boatImg, boat.x, boat.y);
        //spritebatch.draw(bubbleImg, bubble.x, bubble.y);
        spritebatch.end();
    }

    @Override
    public void pause() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void resume() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void dispose() {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    void centerCamera() {
        int xMax = (int) (144 * scaleW), yMax = (int) (160 * scaleH);
        int xMapMax = (int) (144 * testmap.getColumnCount() * scaleW);
        int yMapMax = (int) (160 * testmap.getRowCount() * scaleH);

        camera.position.set(duder.currentPos.x, duder.currentPos.y, 0);
    }
}
