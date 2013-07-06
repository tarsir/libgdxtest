package com.example.libgdxtestgame;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;

import java.util.ArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: stephen
 * Date: 7/5/13
 * Time: 12:35 AM
 * To change this template use File | Settings | File Templates.
 */
public class TextureCache {
    ArrayList<TextureNode> cache;
    int maxSize;


    //Singleton-ize this bitch
    private TextureCache() {
        cache = new ArrayList<TextureNode>();
        maxSize = 20;
    }

    private static class TextureCacheHold {
        public static final TextureCache INSTANCE = new TextureCache();
    }

    public static TextureCache getInstance() {
        return TextureCacheHold.INSTANCE;
    }

    private Texture addTexture(String ident) {
        //get the texture from the ident

        Texture tmp = new Texture(Gdx.files.internal(ident + ".png"));

        TextureNode temp = new TextureNode(tmp, ident);
        int loc = cache.indexOf(temp); //is it already here?
        if (loc == -1) {
            //if we've exceeded the max cache size
            if (cache.size() == 20) {
                cache.remove(0);
            }
            cache.add(temp);
        }
        return tmp;
    }

    public TextureNode loadTexture(String ident) {
        for (TextureNode txtNode : cache) {
            if (txtNode.identifier.equalsIgnoreCase(ident)) {
                return txtNode;
            }
        }
        //if we didn't find it, add it + return it
        System.out.println("texture cache has " + cache.size() + " elements");
        return new TextureNode(addTexture(ident), ident);
    }
}

class TextureNode {
    Texture texture;
    String identifier;

    TextureNode(Texture txt, String ident) {
        texture = txt;
        identifier = ident;
    }
}
