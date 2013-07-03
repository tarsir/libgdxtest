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

/**
 * Created with IntelliJ IDEA.
 * User: stephen
 * Date: 6/3/13
 * Time: 4:04 PM
 * To change this template use File | Settings | File Templates.
 */
public class LibGdxGame implements ApplicationListener {
    Texture boatImg, bubbleImg;
    Music bgMusic;
    Rectangle boat, bubble;

    int scrnWidth, scrnHeight;

    OrthographicCamera camera;
    SpriteBatch spritebatch;

    @Override
    public void create() {
        boatImg = new Texture(Gdx.files.internal("boat.png"));
        bubbleImg = new Texture(Gdx.files.internal("bubble.png"));
        bgMusic = Gdx.audio.newMusic(Gdx.files.internal("bg.mp3"));
        boat = new Rectangle();
        bubble = new Rectangle();
        bubble.x = 100;
        bubble.y = 100;
        bubble.width = 16;
        bubble.height = 16;
        boat.x = 400;
        boat.y = 460;
        boat.width = 32;
        boat.height = 16;

        scrnHeight = 480;
        scrnWidth = 800;

        bgMusic.setLooping(true);
        bgMusic.play();

        camera = new OrthographicCamera();
        camera.setToOrtho(false, scrnWidth, scrnHeight);

        spritebatch = new SpriteBatch();
    }

    @Override
    public void resize(int width, int height) {
        //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public void render() {
        //To change body of implemented methods use File | Settings | File Templates.
        Gdx.gl.glClearColor(0, 0, 0.6f, 1);
        Gdx.gl.glClear(GL10.GL_COLOR_BUFFER_BIT);

        boolean isTouched = Gdx.input.isTouched();
        if (isTouched) {
            boat.x = Gdx.input.getX() - boat.width/2;
            boat.y = scrnHeight - Gdx.input.getY() - boat.height/2;
        }
        bubble.x += 1;
        bubble.y += 1;

        camera.update();

        spritebatch.setProjectionMatrix(camera.combined);
        spritebatch.begin();
        spritebatch.draw(boatImg, boat.x, boat.y);
        spritebatch.draw(bubbleImg, bubble.x, bubble.y);
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
}
