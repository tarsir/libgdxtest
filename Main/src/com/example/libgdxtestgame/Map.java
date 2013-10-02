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
        String temp = file.readString();
        System.out.println("temp=" + temp + "end");
        mapMatrix = temp.split("\\s+");
        /*for (String st : mapMatrix) {
            System.out.print(st);
        } */
        identifier = ident;
    }

    public static String intToTileIdent(Character tilenum) {
        switch (tilenum) {
            case '0':case '\n':
                return "tiles/field_tile1";
            default:
                //System.out.println("tilenum=\"" + tilenum + "\"");
                return "error";
        }
    }

    public int getRowCount() {
        return mapMatrix.length;
    }

    public int getColumnCount() {
        return mapMatrix[0].length();
    }
}