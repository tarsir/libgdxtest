package com.example.libgdxtestgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

import java.util.ArrayList;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: stephen
 * Date: 7/4/13
 * Time: 11:34 PM
 * To change this template use File | Settings | File Templates.
 */

public class Map {
    public String[] mapMatrix;
    public String identifier;

    public Map(String filename, String ident) {
        FileHandle file = Gdx.files.internal("data/" + filename);
        mapMatrix = file.readString().split("\n");
        identifier = ident;
    }

    public static String intToTileIdent(Character tilenum) {
        switch (tilenum) {
            case '0':
                return "tiles/field_tile1";
            default:
                return "error";
        }
    }
}